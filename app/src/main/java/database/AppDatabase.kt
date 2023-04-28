package database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dataAccess.EncodingRequestsDao
import models.EncodingRequest

@Database(entities = [EncodingRequest::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun requestsDao(): EncodingRequestsDao
}