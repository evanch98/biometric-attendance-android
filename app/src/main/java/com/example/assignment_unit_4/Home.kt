package com.example.assignment_unit_4

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.assignment_unit_4.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {
  Scaffold(topBar = {
    TopAppBar(
      title = { Text(text = "Home") },
      colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Purple80)
    )
  }) { contentPadding ->
    Box(modifier = Modifier.padding(contentPadding)) {
      Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp)
      ) {
        Button(onClick = { /*TODO*/ }) {
          Text(text = "Check-in")
        }
      }
    }
  }
}