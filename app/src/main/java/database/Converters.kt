package database

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun toLongFromDate(date: Date): Long = date.time

    @TypeConverter
    fun toDateFromLong(time: Long): Date = Date(time)
}