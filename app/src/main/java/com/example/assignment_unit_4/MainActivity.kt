package com.example.assignment_unit_4

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.assignment_unit_4.ui.theme.Assignment_unit_4Theme

class MainActivity : FragmentActivity() {

  private val database by lazy {
    Room.databaseBuilder(applicationContext, AppDatabase::class.java, "attendanceDatabase").build()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      Assignment_unit_4Theme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
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
