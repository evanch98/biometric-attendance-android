package com.example.assignment_unit_4

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.example.assignment_unit_4.ui.theme.Purple80
import com.example.assignment_unit_4.utils.LocationDetails
import com.example.assignment_unit_4.utils.SnackBar
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.delay

private const val LATITUDE = 37.4219983
private const val LONGITUDE = -122.084

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
  navController: NavController,
  activity: FragmentActivity,
  database: AppDatabase,
) {

  val locationCallback: LocationCallback?
  val fusedLocationClient: FusedLocationProviderClient?

  val context = LocalContext.current

  var currentLocation by remember {
    mutableStateOf(LocationDetails(0.toDouble(), 0.toDouble()))
  }
  fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
  locationCallback = object : LocationCallback() {
    override fun onLocationResult(p0: LocationResult) {
      for (lo in p0.locations) {
        // Update the UI with location data
        currentLocation = LocationDetails(lo.latitude, lo.longitude)
      }
    }
  }

  val launchMultiplePermissions = rememberLauncherForActivityResult(
    ActivityResultContracts.RequestMultiplePermissions()
  ) { permissions ->
    val areGranted = permissions.values.reduce { acc, next -> acc && next }
    if (areGranted) {
      startLocationUpdates(locationCallback, fusedLocationClient)
      Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
    } else {
      Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
    }
  }

  var showSnackBar by remember {
    mutableStateOf(false)
  }

  var snackBarMessage by remember {
    mutableStateOf("")
  }

  val sharedPreferences =
    context.getSharedPreferences("UserAccountData", Context.MODE_PRIVATE)

  val id = context.getSharedPreferences("id", Context.MODE_PRIVATE)

  val name = sharedPreferences.getString("name", "")

  Scaffold(topBar = {
    TopAppBar(
      title = { Text(text = "Home") },
      colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple80)
    )
  }) { contentPadding ->
    val permissions = arrayOf(
      Manifest.permission.ACCESS_COARSE_LOCATION,
      Manifest.permission.ACCESS_FINE_LOCATION
    )
    Box(
      modifier = Modifier
        .padding(contentPadding)
        .fillMaxHeight()
    ) {
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
            if (permissions.all {
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
              }) {
              startLocationUpdates(locationCallback, fusedLocationClient)
            } else {
              launchMultiplePermissions.launch(permissions)
            }
            Biometric.authenticate(
              activity,
              title = "Fingerprint Authentication",
              subtitle = "Authenticate to proceed",
              description = "Authentication is required to check in",
              negativeText = "Cancel",
              onSuccess = {
                if (currentLocation.latitude == LATITUDE && currentLocation.longitude == LONGITUDE) {
                  showSnackBar = true
                  snackBarMessage = "Authentication successful."
                  id.edit().putInt("id", id.getInt("id", 0) + 1).apply()
                } else {
                  showSnackBar = true
                  snackBarMessage = "Authentication unsuccessful. Try Again."
                }
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
            if (permissions.all {
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
              }) {
              startLocationUpdates(locationCallback, fusedLocationClient)
            } else {
              launchMultiplePermissions.launch(permissions)
            }
            Biometric.authenticate(
              activity,
              title = "Fingerprint Authentication",
              subtitle = "Authenticate to proceed",
              description = "Authentication is required to check in",
              negativeText = "Cancel",
              onSuccess = {
                if (currentLocation.latitude == LATITUDE && currentLocation.longitude == LONGITUDE) {
                  showSnackBar = true
                  snackBarMessage = "Authentication successful."
                } else {
                  showSnackBar = true
                  snackBarMessage = "Authentication unsuccessful. Try Again."
                }
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

@SuppressLint("MissingPermission")
private fun startLocationUpdates(
  locationCallback: LocationCallback?,
  fusedLocationClient: FusedLocationProviderClient?
) {
  locationCallback?.let {
    val locationRequest = LocationRequest.create().apply {
      interval = 10000
      fastestInterval = 5000
      priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
    fusedLocationClient?.requestLocationUpdates(
      locationRequest,
      it,
      Looper.getMainLooper()
    )
  }
}
