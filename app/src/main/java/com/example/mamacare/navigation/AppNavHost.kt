package com.example.mamacare.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mamacare.ui.screens.home.HomeScreen
import com.example.mamacare.ui.theme.Screens.login.loginScreen
import com.example.mamacare.ui.theme.Screens.register.registerScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), startDestination:String= ROUTE_REGISTER){
    NavHost(navController=navController,startDestination= startDestination){
//        composable(ROUTE_SPLASH) { SplashScreen {navController.navigate(ROUTE_REGISTER){popUpTo(
//            ROUTE_SPLASH){inclusive = true}} } }
        composable(ROUTE_REGISTER) { registerScreen(navController) }
        composable(ROUTE_LOGIN) { loginScreen(navController) }
        composable(ROUTE_HOMESCREEN){ HomeScreen(navController)}

    }
}