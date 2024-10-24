package com.example.navigation.viewModel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

data class UserInfo(val name: String)

class UserViewModel : ViewModel() {

    private val _userInfo = mutableStateOf(UserInfo(name = ""))
    val userInfo: State<UserInfo> = _userInfo

    fun updateName(newName: String) {
        _userInfo.value = _userInfo.value.copy(name = newName)
    }
}