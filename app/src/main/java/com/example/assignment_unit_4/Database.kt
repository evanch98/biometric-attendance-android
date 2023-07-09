package com.example.assignment_unit_4

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Database(entities = [AttendanceRoom::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
  abstract fun attendanceDao(): AttendanceDao
}

@Entity
data class AttendanceRoom(
  @PrimaryKey val id: Int,
  val checkIn: String,
  val checkOut: String,
)

@Dao
interface AttendanceDao {
  @Query("SELECT * FROM AttendanceRoom")
  fun getAll(): LiveData<List<AttendanceRoom>>
  @Insert
  fun insertAll(vararg attendance: AttendanceRoom)
  @Query("SELECT (SELECT COUNT(*) FROM AttendanceRoom) == 0")
  fun isEmpty(): Boolean
}
