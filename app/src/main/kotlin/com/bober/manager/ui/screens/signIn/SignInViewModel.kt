package com.bober.manager.ui.screens.signIn

import android.R.attr.name
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bober.manager.data.local.MainScreenDataObject
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignInViewModel(
    private val auth: FirebaseAuth,
) : ViewModel() {

    sealed class SignInState {
        object Idle : SignInState()
        object Loading : SignInState()
        data class Success(val data: MainScreenDataObject) : SignInState()
        data class Error(val message: String) : SignInState()
    }

    private val _signInState = MutableStateFlow<SignInState>(SignInState.Idle)
    val signInState: StateFlow<SignInState> = _signInState.asStateFlow()

    fun signIn(email: String, password: String) {
        when {
            email.isBlank() || password.isBlank() -> {
                _signInState.value =
                    SignInState.Error("Поля электронной почты и пароля не могут быть пустыми")
                return
            }

            else -> authenticateUser(email, password)
        }
    }

    private fun authenticateUser(email: String, password: String) {
        viewModelScope.launch {
            _signInState.value = SignInState.Loading
            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                val user = result.user
                if (user != null) {
                    _signInState.value = SignInState.Success(
                        MainScreenDataObject(
                            uid = user.uid,
                            email = user.email ?: "",
                            name = name.toString()
                        )
                    )
                } else {
                    _signInState.value = SignInState.Error("Пользователь не найден")
                }
            } catch (e: Exception) {
                _signInState.value = SignInState.Error(mapFirebaseError(e))
            }
        }
    }

    private fun mapFirebaseError(e: Exception): String {
        return when {
            e.message?.contains("badly formatted") == true -> "Неверный формат email"
            e.message?.contains("password is invalid") == true -> "Неверный пароль"
            e.message?.contains("no user record") == true -> "Пользователь не найден"
            else -> "Ошибка входа: ${e.message ?: "Неизвестная ошибка"}"
        }
    }
}