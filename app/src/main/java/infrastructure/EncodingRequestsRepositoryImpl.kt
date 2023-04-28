package infrastructure

import dataAccess.EncodingRequestsDao
import models.EncodingRequest
import java.sql.Date
import java.time.LocalDateTime
import java.time.ZoneId

class EncodingRequestsRepositoryImpl(private val requestsDao: EncodingRequestsDao) : EncodingRequestsRepository {
    override fun saveRequest(request: String) {
        val addDate = Date.from(
            LocalDateTime.now()
                .atZone(ZoneId.systemDefault())
                .toInstant()
        )
        requestsDao.create(EncodingRequest(0, request, addDate))
    }

    override fun getAllRequests(): List<EncodingRequest> = requestsDao.getAll()
}