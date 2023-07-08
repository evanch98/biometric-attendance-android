package com.example.assignment_unit_4

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.assignment_unit_4.auth.Login
import com.example.assignment_unit_4.auth.SignUp

@Composable
fun Navigation(navController: NavHostController, activity: FragmentActivity) {

  val sharedPreferences =
    LocalContext.current.getSharedPreferences("UserAccountData", Context.MODE_PRIVATE)

  val isRegistered = sharedPreferences.getBoolean("isRegistered", false)

  NavHost(
    navController = navController,
    startDestination = if (isRegistered) Login.route else SignUp.route
  ) {
    composable(SignUp.route) {
      SignUp(navController = navController)
    }
    composable(Login.route) {
      Login(navController = navController)
    }
    composable(Home.route) {
      Home(navController = navController, activity = activity)
    }
  }
}