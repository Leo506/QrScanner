package infrastructure

import android.content.Context

interface EncodingRequestsRepository {
    fun saveRequest(request: String, context: Context)

    fun getAllRequests(): List<String>
}