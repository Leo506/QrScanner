package infrastructure

import models.EncodingRequest

interface EncodingRequestsRepository {
    fun saveRequest(request: String)

    fun getAllRequests(): List<EncodingRequest>
}