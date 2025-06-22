package com.bober.managerfull.ui.screens.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bober.managerfull.data.local.MainScreenDataObject
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignUpViewModel(
    private val auth: FirebaseAuth,
) : ViewModel() {

    sealed class SignUpState {
        object Idle : SignUpState()
        object Loading : SignUpState()
        data class Success(val userData: MainScreenDataObject) : SignUpState()
        data class Error(val message: String) : SignUpState()
    }

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState: StateFlow<SignUpState> = _signUpState.asStateFlow()

    fun signUp(email: String, password: String, name: String) {
        when {
            name.isBlank() -> {
                _signUpState.value = SignUpState.Error("Имя не может быть пустым")
                return
            }

            email.isBlank() || password.isBlank() -> {
                _signUpState.value =
                    SignUpState.Error("Поля электронной почты и пароля не могут быть пустыми")
                return
            }

            password.length < 6 -> {
                _signUpState.value = SignUpState.Error("Пароль должен содержать минимум 6 символов")
                return
            }

            else -> registerUser(email, password, name)
        }
    }

    private fun registerUser(email: String, password: String, name: String) {
        viewModelScope.launch {
            _signUpState.value = SignUpState.Loading
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                val user = result.user
                if (user != null) {
                    _signUpState.value = SignUpState.Success(
                        MainScreenDataObject(
                            uid = user.uid,
                            email = user.email ?: "",
                            name = name
                        )
                    )
                } else {
                    _signUpState.value = SignUpState.Error("Ошибка при создании пользователя")
                }
            } catch (e: Exception) {
                _signUpState.value = SignUpState.Error(mapFirebaseError(e))
            }
        }
    }

    private fun mapFirebaseError(e: Exception): String {
        return when {
            e.message?.contains("email address is already in use") == true -> "Email уже используется"
            e.message?.contains("badly formatted") == true -> "Неверный формат email"
            e.message?.contains("password is weak") == true -> "Пароль слишком слабый"
            else -> "Ошибка регистрации: ${e.message ?: "Неизвестная ошибка"}"
        }
    }
}