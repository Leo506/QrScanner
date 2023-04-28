package dataAccess

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import models.EncodingRequest

@Dao
interface EncodingRequestsDao {
    @Query("SELECT * FROM encoding_requests")
    fun getAll(): List<EncodingRequest>

    @Insert
    fun create(request:EncodingRequest)
}