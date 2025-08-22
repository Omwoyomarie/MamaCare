package com.example.mamacare.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mamacare.ui.screens.HomeScreen
//import com.example.mamacare.ui.screens.medicalinfo.ViewMedicalInfoScreen
import com.example.mamacare.ui.theme.Screens.MedicalInfo.MedicalInfoScreen
import com.example.mamacare.ui.theme.Screens.MedicalInfo.UpdateMedicalInfoScreen
import com.example.mamacare.ui.theme.Screens.MedicalInfo.ViewMedicalInfoScreen
import com.example.mamacare.ui.theme.Screens.SplashScreen
import com.example.mamacare.ui.theme.Screens.UserScreen.userScreen
//import com.example.mamacare.ui.theme.Screens.MedicalInfo.ViewMedicalInfoScreen
//import com.example.mamacare.ui.theme.Screens.contact.AddContactScreen
import com.example.mamacare.ui.theme.Screens.contact.AddContactsScreen
import com.example.mamacare.ui.theme.Screens.contact.UpdateContactsScreen
import com.example.mamacare.ui.theme.Screens.contact.ViewContactsScreen
//import com.example.mamacare.ui.screens.home.HomeScreen
import com.example.mamacare.ui.theme.Screens.login.loginScreen
import com.example.mamacare.ui.theme.Screens.register.registerScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), startDestination:String= ROUTE_SPLASH){
    NavHost(navController=navController,startDestination= startDestination){
        composable(ROUTE_SPLASH) { SplashScreen {navController.navigate(ROUTE_USER_SCREEN){popUpTo(
            ROUTE_USER_SCREEN){inclusive = true}} } }
        composable(ROUTE_REGISTER) { registerScreen(navController) }
        composable(ROUTE_LOGIN) { loginScreen(navController) }
        composable(ROUTE_HOMESCREEN){ HomeScreen(navController)}
        composable(ROUTE_CONTACT) { AddContactsScreen(navController) }
        composable(ROUTE_VIEW_CONTACTS) { ViewContactsScreen(navController) }
        composable(ROUTE_USER_SCREEN) { userScreen(navController) }

        composable(
            ROUTE_UPDATE_CONTACTS,
            arguments = listOf(navArgument("contactId") { type = NavType.StringType })
        ) { backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId")!!
            UpdateContactsScreen(navController, contactId)
        }
        composable(ROUTE_MEDICAL_INFO) { MedicalInfoScreen(navController) }
        composable(ROUTE_VIEW_MEDICAL_INFO) { ViewMedicalInfoScreen(navController)}
        composable(
            route = "update_medical_info/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            UpdateMedicalInfoScreen(navController, backStackEntry)
        }








    }
}