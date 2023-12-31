package com.example.assignment_unit_4.auth

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assignment_unit_4.Home
import com.example.assignment_unit_4.ui.theme.Purple80
import com.example.assignment_unit_4.utils.SnackBar
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavController) {

  val sharedPreferences =
    LocalContext.current.getSharedPreferences("UserAccountData", Context.MODE_PRIVATE)

  val registeredEmail = sharedPreferences.getString("email", "")
  val registeredPassword = sharedPreferences.getString("password", "")

  var email by remember {
    mutableStateOf("")
  }

  var password by remember {
    mutableStateOf("")
  }

  var showSnackBar by remember {
    mutableStateOf(false)
  }

  var snackBarMessage by remember {
    mutableStateOf("")
  }

  Scaffold(topBar = {
    TopAppBar(
      title = { Text(text = "Login") },
      colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple80)
    )
  }) { contentPadding ->
    Box(modifier = Modifier.padding(contentPadding)) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp),
      ) {
        Text("Welcome Back", fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Please Log In to continue", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
          value = email,
          onValueChange = { email = it },
          label = { Text(text = "Email") },
          placeholder = {
            Text(
              text = "johndoe@uopeople.edu"
            )
          }, modifier = Modifier.fillMaxWidth(),
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
          value = password,
          onValueChange = { password = it },
          label = { Text(text = "Password") },
          modifier = Modifier.fillMaxWidth(),
          keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
          visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
          onClick = {
            showSnackBar = true
                    snackBarMessage = if (email == registeredEmail && password == registeredPassword) {
                      navController.navigate(Home.route)
                      "Logged in successfully"
                    } else {
                      "Login failed. Email or password is incorrect."
                    }
          }, modifier = Modifier
            .width(250.dp)
            .height(50.dp)
        ) {
          Text(text = "Log In", fontSize = 20.sp)
        }
        if (showSnackBar) {
          SnackBar(message = snackBarMessage)
          LaunchedEffect(Unit) {
            delay(2000)
            showSnackBar = false
          }
        }
      }
    }
  }
}