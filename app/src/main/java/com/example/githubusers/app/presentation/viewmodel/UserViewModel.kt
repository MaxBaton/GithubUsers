package com.example.githubusers.app.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers.domain.models.User
import com.example.githubusers.domain.models.UserDetail
import com.example.githubusers.domain.usecase.GetAllUsers
import com.example.githubusers.domain.usecase.GetUserDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getAllUsers: GetAllUsers,
    private val getUserDetail: GetUserDetail
): ViewModel() {
    // All Users
    private val mutableUsersLiveData = MutableLiveData<List<User>?>()
    val usersLiveData = mutableUsersLiveData
    // UserDeatil
    private val mutableUserDetailLiveData = MutableLiveData<UserDetail>()
    val userDetailLiveData = mutableUserDetailLiveData

    fun getAllUsers(onSuccess: () -> Unit, onError: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val users = getAllUsers.get()
            CoroutineScope(Dispatchers.Main).launch {
                if (users != null) {
                    onSuccess()
                    mutableUsersLiveData.value = users
                }else {
                    onError()
                }
            }
        }
    }

    fun getUserDetail(login: String, onSuccess: () -> Unit, onError: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val userDetail = getUserDetail.getByLogin(login = login)
            CoroutineScope(Dispatchers.Main).launch {
                if (userDetail != null) {
                    onSuccess()
                    userDetail?.let { mutableUserDetailLiveData.value = it }
                }else {
                    onError()
                }
            }
        }
    }
}