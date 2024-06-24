package com.dhruv.datarendering

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class Screen (val route: String){
    object MainScreen : Screen("main_screen")
}

@Composable
fun NavigationControl(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val vm : ExampleDataViewModel = viewModel()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route){
            MainScreen(navController = navController , vm = vm)
        }
    }
}