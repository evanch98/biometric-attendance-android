package com.example.assignment_unit_4.utils

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun SnackBar(
  message: String,
  duration: SnackbarDuration = SnackbarDuration.Short,
) {
  val snackBarHostState = remember { SnackbarHostState() }

  LaunchedEffect(snackBarHostState) {
    snackBarHostState.showSnackbar(message, duration = duration)
  }
  SnackbarHost(hostState = snackBarHostState)
}
