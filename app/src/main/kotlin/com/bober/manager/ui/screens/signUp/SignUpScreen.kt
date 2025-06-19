package com.bober.manager.ui.screens.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bober.manager.navigation.HomeRoute
import com.bober.manager.navigation.SignInRoute
import com.bober.manager.ui.components.LoginButton
import com.bober.manager.ui.components.RoundedCorner
import com.bober.manager.ui.components.RoundedCornerPassword
import com.bober.manager.ui.theme.Cyellow
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun SignUpScreen(navController: NavHostController) {
    val auth = remember { Firebase.auth }
    val viewModel: SignUpViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SignUpViewModel(auth) as T
            }
        }
    )

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val signUpState by viewModel.signUpState.collectAsState()

    // Обработка успешной регистрации
    LaunchedEffect(signUpState) {
        when (val state = signUpState) {
            is SignUpViewModel.SignUpState.Success -> {
                // Навигация на Home экран с очисткой стека навигации
                navController.navigate(HomeRoute) {
                    popUpTo(0) // Очищаем весь стек навигации
                }
            }

            is SignUpViewModel.SignUpState.Error -> {
                errorMessage = state.message
            }

            else -> {}
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 80.dp)
                    .size(44.dp)
                    .background(Cyellow, shape = CircleShape)
                    .clickable {
                        navController.navigate(SignInRoute)
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(24.dp)
                )
            }

            Text(
                text = "Регистрация",
                fontSize = 32.sp,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp)
            )

            Text(
                text = "Заполните Свои Данные Или Продолжите Через Социальные Медиа",
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp, bottom = 24.dp)
                    .fillMaxWidth(0.85f)
            )

            Text(
                text = "Ваше имя",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
            )

            RoundedCorner(
                text = name,
                label = "xxxxxxxx",
            ) { name = it }

            Text(
                text = "Email",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
            )

            RoundedCorner(
                text = email,
                label = "xyz@gmail.com",
            ) { email = it }

            Text(
                text = "Пароль",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
            )

            RoundedCornerPassword(
                text = password,
                label = "••••••••",
            ) { password = it }

            Spacer(modifier = Modifier.height(20.dp))

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoginButton(
                    text = "Регистрация",
                    onClick = {
                        errorMessage = ""
                        viewModel.signUp(email, password, name)
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.padding(top = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Есть аккаунт?",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Войти",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .clickable { navController.navigate(SignInRoute) }
                            .padding(start = 4.dp)
                    )
                }
            }
        }
    }
}