// MainActivity.kt
package com.example.jiggyfit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jiggyfit.navigation.Routes
import com.example.jiggyfit.ui.home.HomeScreen
import com.example.jiggyfit.ui.theme.JiggyFitTheme
import com.example.jiggyfit.ui.login.ui.LoginScreen
import com.example.jiggyfit.ui.login.ui.LoginViewModel
import com.example.jiggyfit.ui.login.ui.RegisterScreen
import com.example.jiggyfit.ui.login.ui.RegisterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JiggyFitTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background)
                {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home) {
        composable(Routes.Login) { LoginScreen(navController = navController, viewModel = LoginViewModel()) }
        composable(Routes.Register) { RegisterScreen(navController = navController, viewModel = RegisterViewModel()) }
        composable(Routes.Home) { HomeScreen(navController = navController) }
    }
}


