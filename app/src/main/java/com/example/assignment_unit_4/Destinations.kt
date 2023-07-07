package com.example.assignment_unit_4

interface Destinations {
  val route: String
}

object SignUp: Destinations {
  override val route: String = "SignUp"
}

object Login: Destinations {
  override val route: String = "Login"
}
