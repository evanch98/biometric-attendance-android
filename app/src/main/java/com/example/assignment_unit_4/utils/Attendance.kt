package com.example.assignment_unit_4.utils

import com.example.assignment_unit_4.AttendanceRoom

data class Attendance(val id: Int, val checkIn: String, val checkOut: String) {
  fun toAttendanceRoom() = AttendanceRoom(
    id,
    checkIn,
    checkOut
  )
}
