package com.example.githubusers.app.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers.domain.models.User
import com.example.githubusers.domain.models.UserDetail
import com.example.githubusers.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getAllUsers: GetAllUsers,
    private val getAllUsersFromDb: GetAllUsersFromDb,
    private val getUserDetail: GetUserDetail,
    private val saveUsers: SaveUsers,
    private val deleteUsersFromDb: DeleteUsersFromDb,
    private val saveUserDetail: SaveUserDetail,
    private val getUserDetailFromDb: GetUserDetailFromDb
): ViewModel() {
    private companion object {
        const val SAVE_USER_COUNT = 10
    }

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
                    userDetail?.let { mutableUserDetailLiveData.value = it }
                    onSuccess()
                }else {
                    onError()
                }
            }
        }
    }

    fun getAllUsersFromDb(onSuccess: () -> Unit, onError: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val users = getAllUsersFromDb.get()
            CoroutineScope(Dispatchers.Main).launch {
                if (users != null && users.isNotEmpty()) {
                    mutableUsersLiveData.value = users
                    onSuccess()
                }else {
                    onError()
                }
            }
        }
    }

    fun saveFirstTenUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            val users = getFirstTenUsers()
            val isSave = saveUsers.saveUsers(users = users)
        }
    }

    private fun getFirstTenUsers(): List<User> {
        val users = mutableListOf<User>()

        usersLiveData.value?.let {
            it.forEachIndexed { index, user ->
                if (index == SAVE_USER_COUNT) {
                    return users
                }
                users.add(user)
            }
        }

        return users.toImmutableList()
    }

    fun deleteAllUsersFromDb(onSuccess: () -> Unit, onError: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch{
            val isDelete = deleteUsersFromDb.delete()
            CoroutineScope(Dispatchers.Main).launch {
                if (isDelete) {
                    onSuccess()
                }else {
                    onError()
                }
            }
        }
    }

    fun saveUserDetailToDb() {
        CoroutineScope(Dispatchers.IO).launch {
            val userDetail = userDetailLiveData.value
            userDetail?.let { saveUserDetail.save(userDetail = it) }
        }
    }

    fun getUserDetailFromDb(login: String, onSuccess: () -> Unit, onError: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val userDetail = getUserDetailFromDb.getByLogin(login = login)
            CoroutineScope(Dispatchers.Main).launch {
                if (userDetail != null) {
                    userDetail?.let { mutableUserDetailLiveData.value = it }
                    onSuccess()
                }else {
                    onError()
                }
            }
        }
    }
}