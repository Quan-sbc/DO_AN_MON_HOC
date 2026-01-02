package com.example.app.ui.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

data class RegisterUser(
    val userName: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = "",
    val dateOfBirth: String = "",
)

class RegisterViewModel : ViewModel() {
    private val _user = MutableStateFlow(RegisterUser())
    val user: StateFlow<RegisterUser> = _user.asStateFlow()

    fun updateUserName(value: String) {
        _user.value = _user.value.copy(userName = value)
    }

    fun updateEmail(value: String) {
        _user.value = _user.value.copy(email = value)
    }

    fun updatePhone(value: String) {
        _user.value = _user.value.copy(phone = value)
    }

    fun updatePassword(value: String) {
        _user.value = _user.value.copy(password = value)
    }

    fun updateDateOfBirth(value: String) {
        _user.value = _user.value.copy(dateOfBirth = value)
    }

    // Khi hoàn tất, lấy toàn bộ data
    fun getFinalUser() = _user.value.copy() // bỏ OTP đi
}