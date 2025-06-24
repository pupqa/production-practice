package com.bober.managerfull

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bober.managerfull.navigation.Navigation
import com.bober.managerfull.ui.theme.ManagerfullTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ManagerfullTheme {
                Navigation()
            }
        }
    }
}

