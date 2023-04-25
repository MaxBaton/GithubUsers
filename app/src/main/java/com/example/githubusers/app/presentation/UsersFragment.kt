package com.example.githubusers.app.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.githubusers.R
import com.example.githubusers.app.presentation.viewmodel.UserViewModel
import com.example.githubusers.databinding.FragmentUsersBinding
import com.example.githubusers.databinding.HeaderUserItemBinding
import com.example.githubusers.databinding.UserItemLayoutBinding
import com.example.githubusers.domain.models.User
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.viewbinding.BindableItem

class UsersFragment: Fragment() {
    private var binding: FragmentUsersBinding? = null
    private val userViewModel: UserViewModel by activityViewModels()
    // Recycler
    private val groupieAdapter = GroupieAdapter()
    private val section = Section()
    private var initialList: List<UserItem>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding ?: return) {
            section.apply {
                setHeader(HeaderUserItem())
            }

            groupieAdapter.add(section)

            recyclerViewUsers.apply {
                adapter = groupieAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            }

            userViewModel.usersLiveData.observe(viewLifecycleOwner) { users ->
                val usersItem = mutableListOf<UserItem>()
                users?.forEach {
                    usersItem.add(UserItem(user = it))
                }
                initialList = usersItem

                section.update(usersItem)
            }

            if (userViewModel.usersLiveData.value == null ||
                userViewModel.usersLiveData.value?.isEmpty() == true
            ) {
                userViewModel.getAllUsers(
                    onSuccess = {
                        progressBar.visibility = View.GONE
                        userViewModel.deleteAllUsersFromDb(
                            onSuccess = {
                                userViewModel.saveFirstTenUsers()
                            },
                            onError = {

                            }
                        )
                    },
                    onError = {
                        progressBar.visibility = View.GONE
                        userViewModel.getAllUsersFromDb(
                            onSuccess = {
                                Toast.makeText(requireContext(), "Ошибка загрузки. Вы работаете в офлайн режиме", Toast.LENGTH_SHORT).show()
                            },
                            onError = {
                                Toast.makeText(requireContext(), "Ошибка загрузки пользователей", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                )
            }else {
                progressBar.visibility = View.GONE
            }

            groupieAdapter.setOnItemClickListener { item, view ->
                val userItem = item as UserItem
                val login = userItem.user.login
                userViewModel.getUserDetail(
                    login = login,
                    onSuccess = {
                        userViewModel.saveUserDetailToDb()
                        findNavController().navigate(R.id.action_usersFragment_to_userDetailFragment)
                    },
                    onError = {
                        userViewModel.getUserDetailFromDb(
                            login = login,
                            onSuccess = {
                                findNavController().navigate(R.id.action_usersFragment_to_userDetailFragment)
                            },
                            onError = {
                                Toast.makeText(requireContext(), "Ошибка получения данных о пользователе", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                )
            }
        }
    }

    private fun filterUserInfo(info: String) {
        if (info.isEmpty()) {
            initialList?.let {
                section.update(it)
            }
        }else {
            initialList?.let {
                val filteredList = it.filter { userItem ->
                    val user = userItem.user
                    user.login.toUpperCase().contains(info.toUpperCase()) ||
                        user.id.toString().toUpperCase().contains(info.toUpperCase())
                }
                section.update(filteredList)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        groupieAdapter.remove(section)
    }

    inner class UserItem(val user: User): BindableItem<UserItemLayoutBinding>() {
        override fun bind(viewBinding: UserItemLayoutBinding, position: Int) {
            with(viewBinding) {
                textViewTitle.text = user.login
                textViewSubtitle.text = user.id.toString()

                Glide
                    .with(requireContext())
                    .load(user.avatar_url)
                    .placeholder(R.drawable.icon_update)
                    .into(imageViewAvatar)
            }
        }

        override fun getLayout() = R.layout.user_item_layout

        override fun initializeViewBinding(view: View) = UserItemLayoutBinding.bind(view)

    }

    inner class HeaderUserItem: BindableItem<HeaderUserItemBinding>() {
        override fun bind(viewBinding: HeaderUserItemBinding, position: Int) {
            with(viewBinding) {
                searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        newText?.let { str ->
                            filterUserInfo(info = str.trim())
                        }
                        return true
                    }

                })
            }
        }

        override fun getLayout() = R.layout.header_user_item

        override fun initializeViewBinding(view: View) = HeaderUserItemBinding.bind(view)

    }
}