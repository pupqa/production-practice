package com.bober.manager.ui.screens.signIn

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
import androidx.compose.material3.MaterialTheme
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bober.manager.navigation.HomeRoute
import com.bober.manager.navigation.SignInRoute
import com.bober.manager.navigation.SignUpRoute
import com.bober.manager.ui.components.LoginButton
import com.bober.manager.ui.components.RoundedCorner
import com.bober.manager.ui.components.RoundedCornerPassword
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun SignInScreen(navController: NavHostController) {
    val auth = remember { Firebase.auth }
    val viewModel: SignInViewModel = viewModel(
        factory = object : androidx.lifecycle.ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return SignInViewModel(auth) as T
            }
        }
    )

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val signInState by viewModel.signInState.collectAsState()

    // Обработка состояний
    LaunchedEffect(signInState) {
        when (val state = signInState) {
            is SignInViewModel.SignInState.Success -> {
                navController.navigate(HomeRoute) {
                    popUpTo(SignInRoute) { inclusive = true }
                }
            }

            is SignInViewModel.SignInState.Error -> {
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
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(150.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Привет", color = Color.White, fontSize = 32.sp)
                Text(text = "!", color = Color.White, fontSize = 32.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Заполните Свои Данные Или Продолжите Через Социальные Медиа",
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(0.9f)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Email",
                    color = Color.White,
                    textAlign = TextAlign.End,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            RoundedCorner(
                text = email,
                label = "xyz@gmail.com",
            ) { email = it }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Пароль",
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            RoundedCornerPassword(
                text = password,
                label = "••••••••",
            ) { password = it }
            Spacer(modifier = Modifier.height(10.dp))
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp), // Отступ снизу для всего контента
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween // Распределение пространства между элементами
                ) {
                    // Пустое пространство вверху, которое займет все доступное место
                    Spacer(modifier = Modifier.weight(1f))

                    // Основной контент (кнопка и текст)
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 32.dp), // Отступ от системных кнопок
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LoginButton(
                            text = "Войти",
                            onClick = { viewModel.signIn(email, password) },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Row(
                            modifier = Modifier.padding(top = 24.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Вы впервые?",
                                color = Color.White,
                                fontSize = 16.sp
                            )
                            Text(
                                text = " Создать пользователя",
                                fontSize = 16.sp,
                                color = Color.LightGray,
                                modifier = Modifier.clickable {
                                    navController.navigate(SignUpRoute)
                                }
                            )
                        }
                    }
                }

                }
            }

    }
}
