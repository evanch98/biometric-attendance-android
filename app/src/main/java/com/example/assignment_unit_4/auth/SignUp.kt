package com.example.assignment_unit_4.auth


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp() {

  var name by remember {
    mutableStateOf("")
  }

  Scaffold(topBar = {
    TopAppBar(title = { Text(text = "Sign Up") })
  }) { contentPadding ->
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier
        .fillMaxWidth()
        .padding(contentPadding),
    ) {
      OutlinedTextField(
        value = name,
        onValueChange = { name = it },
        label = { Text(text = "Name") },
        placeholder = {
          Text(
            text = "John"
          )
        }, modifier = Modifier.fillMaxWidth()
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
  SignUp()
}