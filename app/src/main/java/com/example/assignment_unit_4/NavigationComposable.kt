package com.example.assignment_unit_4

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController) {
  NavHost(navController = navController, startDestination = SignUp.route) {
    composable(SignUp.route) {

    }
  }
}