package com.bober.manager.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.bober.manager.R

@Composable
fun HomeScreen(navController: NavHostController) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    )
    {
        Image(
            painter = painterResource(R.drawable.logo_1),
            modifier = Modifier.fillMaxWidth(0.6f),
            contentDescription = null
        )
    }

}