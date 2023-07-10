package com.example.assignment_unit_4.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun getCurrentTime(): String {
  val current = LocalDateTime.now()
  val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
  return current.format(formatter)
}
