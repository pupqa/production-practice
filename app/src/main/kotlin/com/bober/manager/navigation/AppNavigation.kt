package com.bober.manager.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bober.manager.ui.screens.home.HomeScreen
import com.bober.manager.ui.screens.signIn.SignInScreen
import com.bober.manager.ui.screens.signUp.SignUpScreen
import com.bober.manager.ui.screens.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = SplashRoute
    ) {
        composable<SplashRoute> {
            SplashScreen(navController)
        }
        composable<SignInRoute> {
            SignInScreen(navController)
        }
        composable<SignUpRoute> {
            SignUpScreen(navController)
        }
        composable<HomeRoute> {
            HomeScreen(navController)
        }
    }
}