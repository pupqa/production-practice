package com.bober.managerfull.ui.screens.signIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.bober.managerfull.R
import com.bober.managerfull.navigation.OfficeMapScreenRoute
import com.bober.managerfull.navigation.SignInRoute
import com.bober.managerfull.navigation.SignUpRoute
import com.bober.managerfull.ui.components.loginScreen.LoginButton
import com.bober.managerfull.ui.components.loginScreen.RoundedCorner
import com.bober.managerfull.ui.components.loginScreen.RoundedCornerPassword
import com.bober.managerfull.ui.theme.Yellow
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun SignInScreen(
    navController: NavHostController,
) {

    val auth = remember { Firebase.auth }

    val viewModel: SignInViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SignInViewModel(auth) as T
            }
        }
    )

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val signInState by viewModel.signInState.collectAsState()

    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    /*
        LaunchedEffect(Unit) {
            emailFocusRequester.requestFocus() // Автофокус на email при открытии
        }
    */
    LaunchedEffect(signInState) {
        when (val state = signInState) {
            is SignInViewModel.SignInState.Success -> {
                navController.navigate(OfficeMapScreenRoute) {
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
            Spacer(modifier = Modifier.fillMaxHeight(0.08f))
            Image(
                painter = painterResource(R.drawable.logo_code_inside_oneline),
                modifier = Modifier.fillMaxWidth(),
                contentDescription = null
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.06f))

            Text(
                text = stringResource(R.string.text_hello),
                fontSize = 32.sp,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp)
            )

            Text(
                text = stringResource(R.string.text_welcome_sign_in_up),
                fontSize = 16.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp, bottom = 10.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.fillMaxHeight(0.06f))

            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.hint_email),
                    color = Color.White,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 16.dp, bottom = 10.dp)
                )
            }

            RoundedCorner(
                text = email,
                label = stringResource(R.string.hint_email_example),
                onValueChange = { email = it },
                focusRequester = emailFocusRequester,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(
                    onNext = { passwordFocusRequester.requestFocus() }
                )
            )

            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.hint_password),
                    fontSize = 16.sp,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 16.dp, bottom = 10.dp)
                )
            }

            RoundedCornerPassword(
                text = password,
                label = stringResource(R.string.hint_password_masked),
                onValueChange = { password = it },
                focusRequester = passwordFocusRequester,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { viewModel.signIn(email, password) }
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Spacer(modifier = Modifier.weight(1f))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LoginButton(
                            text = stringResource(R.string.button_login),
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                viewModel.signIn(email, password)
                            }
                        )

                        Row(
                            modifier = Modifier.padding(top = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(R.string.text_first_time_question),
                                color = Color.White,
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 16.sp
                            )
                            Text(
                                text = stringResource(R.string.clickable_text_create_user),
                                fontSize = 16.sp,
                                color = Yellow,
                                style = MaterialTheme.typography.bodyMedium,
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