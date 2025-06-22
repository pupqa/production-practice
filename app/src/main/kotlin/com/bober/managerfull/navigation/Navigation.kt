package com.bober.managerfull.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bober.managerfull.OfficeViewModel
import com.bober.managerfull.ui.screens.officeMap.OfficeMapScreen
import com.bober.managerfull.ui.screens.officeMap.OfficeMapScreenViewModel
import com.bober.managerfull.ui.screens.signIn.SignInScreen
import com.bober.managerfull.ui.screens.signUp.SignUpScreen
import com.bober.managerfull.ui.screens.splash.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModelOffice = viewModel<OfficeMapScreenViewModel>()
    val officeViewModel = viewModel<OfficeViewModel>()

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
        composable<OfficeMapScreenRoute> {
            OfficeMapScreen(
                navController,
                viewModelOffice,
                officeViewModel
            )
        }
    }
}

