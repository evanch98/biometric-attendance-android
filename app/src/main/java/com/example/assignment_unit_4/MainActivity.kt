package com.example.assignment_unit_4

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.assignment_unit_4.ui.theme.Assignment_unit_4Theme

class MainActivity : FragmentActivity() {

  private val database by lazy {
    Room.databaseBuilder(applicationContext, AppDatabase::class.java, "attendanceDatabase").build()
  }

  private val requiredPermissions = arrayOf(
    Manifest.permission.ACCESS_COARSE_LOCATION,
    Manifest.permission.ACCESS_FINE_LOCATION
  )

  private val locationPermissionRequest = registerForActivityResult(
    ActivityResultContracts.RequestMultiplePermissions()
  ) { permissions ->
    when {
      requiredPermissions.all {
        ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
      } -> {
        Toast.makeText(this, "Access granted", Toast.LENGTH_SHORT).show()
      }

      permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
        Toast.makeText(this, "Access granted", Toast.LENGTH_SHORT).show()
      }

      permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
        Toast.makeText(this, "Access granted", Toast.LENGTH_SHORT).show()
      }
      else -> {
        Toast.makeText(this, "Access denied", Toast.LENGTH_SHORT).show()
      }
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      Assignment_unit_4Theme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          MyNavigation(this@MainActivity, database, locationPermissionRequest)
        }
      }
    }
  }
}

@Composable
fun MyNavigation(
  activity: FragmentActivity,
  database: AppDatabase,
  locationPermissionRequest: ActivityResultLauncher<Array<String>>
) {
  val navController = rememberNavController()
  Navigation(
    navController = navController,
    activity = activity,
    database = database,
    locationPermissionRequest = locationPermissionRequest
  )
}
