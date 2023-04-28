package models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "encoding_requests")
data class EncodingRequest(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val requestText: String,
    val requestDate: Date
)