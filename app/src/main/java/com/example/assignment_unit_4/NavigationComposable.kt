package com.example.assignment_unit_4

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.assignment_unit_4.auth.Login
import com.example.assignment_unit_4.auth.SignUp

@Composable
fun Navigation(navController: NavHostController) {
  NavHost(navController = navController, startDestination = SignUp.route) {
    composable(SignUp.route) {
      SignUp(navController = navController)
    }
    composable(Login.route) {
      Login(navController = navController)
    }
    composable(Home.route) {
      Home(navController = navController)
    }
  }
}