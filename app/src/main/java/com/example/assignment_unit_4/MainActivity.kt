package com.example.assignment_unit_4

import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.assignment_unit_4.ui.theme.Assignment_unit_4Theme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback

class MainActivity : FragmentActivity() {

  private var locationCallback: LocationCallback? = null
  var fusedLocationClient: FusedLocationProviderClient? = null
  private var locationRequired: Boolean = false

  private val database by lazy {
    Room.databaseBuilder(applicationContext, AppDatabase::class.java, "attendanceDatabase").build()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      Assignment_unit_4Theme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          val context = LocalContext.current

          MyNavigation(this@MainActivity, database)
        }
      }
    }
  }
}

@Composable
fun MyNavigation(activity: FragmentActivity, database: AppDatabase) {
  val navController = rememberNavController()
  Navigation(navController = navController, activity = activity, database = database)
}
