package com.bober.managerfull.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.bober.managerfull.R
import com.bober.managerfull.navigation.SignInRoute
import com.bober.managerfull.ui.components.splashScreen.VideoPlayerScreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
) {
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(SignInRoute) {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {

        VideoPlayerScreen(
            videoResId = R.raw.video,
            modifier = Modifier.fillMaxSize(0.9f),
            autoPlay = true,
            loop = true
        )
        /* Image(
            painter = painterResource(R.drawable.logo_code_inside),
            modifier = Modifier.fillMaxWidth(0.6f),
            contentDescription = null
           ) */
    }
}
