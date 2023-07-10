package com.example.assignment_unit_4

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assignment_unit_4.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewAttendance(navController: NavController, database: AppDatabase) {
  val databaseAttendance by database.attendanceDao().getAll().observeAsState(emptyList())
  Scaffold(topBar = {
    TopAppBar(
      title = { Text(text = "Attendances") },
      colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple80)
    )
  }) { contentPadding ->
    Box(modifier = Modifier.padding(contentPadding)) {
      AttendanceList(items = databaseAttendance)
    }
  }
}

@Composable
fun AttendanceList(items: List<AttendanceRoom>) {
  Column(modifier = Modifier.padding(20.dp)) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
      Text(text = "No.", fontWeight = FontWeight.Bold, fontSize = 24.sp)
      Text(text = "Clock In", fontWeight = FontWeight.Bold, fontSize = 24.sp)
      Text(text = "Clock Out", fontWeight = FontWeight.Bold, fontSize = 24.sp)
    }
    LazyColumn {
      items(
        items = items,
        itemContent = { attendanceItem ->
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(text = attendanceItem.id.toString(), fontSize = 18.sp)
            Text(text = attendanceItem.checkIn, fontSize = 18.sp)
            Text(text = attendanceItem.checkOut, fontSize = 18.sp)
          }
        }
      )
    }
  }
}
