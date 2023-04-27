package infrastructure

import android.content.Context
import java.io.File

class EncodingRequestsRepositoryImpl(private val context: Context) : EncodingRequestsRepository {
    override fun saveRequest(request: String, context: Context) {
        val path = context.filesDir
        val dataFile = File(path, "data.txt")
        dataFile.appendText(request + '\n')
    }

    override fun getAllRequests(): List<String> {
        val path = context.filesDir
        val dataFile = File(path, "data.txt")
        return dataFile.readLines()
    }
}