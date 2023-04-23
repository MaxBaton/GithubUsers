package com.example.githubusers.app.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.githubusers.app.presentation.viewmodel.UserViewModel
import com.example.githubusers.databinding.FragmentUserDetailBinding

class UserDetailFragment: Fragment() {
    private var binding: FragmentUserDetailBinding? = null
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding ?: return) {
            userViewModel.userDetailLiveData.observe(viewLifecycleOwner) { userDetail ->
                (requireActivity() as AppCompatActivity).supportActionBar?.title = userDetail.name

                Glide
                    .with(requireContext())
                    .load(userDetail.avatar_url)
                    .into(imageViewAvatar)

                textViewLogin.text = userDetail.login
                textViewLocation.text = userDetail.location
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}