package com.example.assignment_unit_4

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.example.assignment_unit_4.ui.theme.Purple80
import com.example.assignment_unit_4.utils.SnackBar
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController, activity: FragmentActivity) {

  val sharedPreferences =
    LocalContext.current.getSharedPreferences("UserAccountData", Context.MODE_PRIVATE)

  val name = sharedPreferences.getString("name", "")

  var showSnackBar by remember {
    mutableStateOf(false)
  }

  var snackBarMessage by remember {
    mutableStateOf("")
  }

  Scaffold(topBar = {
    TopAppBar(
      title = { Text(text = "Home") },
      colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple80)
    )
  }) { contentPadding ->
    Box(modifier = Modifier
      .padding(contentPadding)
      .fillMaxHeight()) {
      Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp)
      ) {
        Text(text = "Welcome back, $name", fontWeight = FontWeight.Bold, fontSize = 25.sp)
        Spacer(modifier = Modifier.height(30.dp))
        Button(
          onClick = {
            Biometric.authenticate(
              activity,
              title = "Fingerprint Authentication",
              subtitle = "Authenticate to proceed",
              description = "Authentication is required to check in",
              negativeText = "Cancel",
              onSuccess = {
                showSnackBar = true
                snackBarMessage = "Authentication Successful"
              },
              onError = { errorCode, errorString ->
                run {
                  showSnackBar = true
                  snackBarMessage = "Error: $errorCode, $errorString"
                }
              },
              onFailed = {
                showSnackBar = true
                snackBarMessage = "Authentication Failed"
              }
            )
          }, modifier = Modifier
            .width(250.dp)
            .height(50.dp)
        ) {
          Text(text = "Check-in", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
          onClick = {
            Biometric.authenticate(
              activity,
              title = "Fingerprint Authentication",
              subtitle = "Authenticate to proceed",
              description = "Authentication is required to check in",
              negativeText = "Cancel",
              onSuccess = {
                showSnackBar = true
                snackBarMessage = "Authentication Successful"
              },
              onError = { errorCode, errorString ->
                run {
                  showSnackBar = true
                  snackBarMessage = "Error: $errorCode, $errorString"
                }
              },
              onFailed = {
                showSnackBar = true
                snackBarMessage = "Authentication Failed"
              }
            )
          }, modifier = Modifier
            .width(250.dp)
            .height(50.dp)
        ) {
          Text(text = "Check-out", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
          onClick = { /*TODO*/ }, modifier = Modifier
            .width(250.dp)
            .height(50.dp)
        ) {
          Text(text = "View Attendance", fontSize = 20.sp)
        }
      }
      Box(modifier = Modifier.align(alignment = Alignment.BottomCenter)) {
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