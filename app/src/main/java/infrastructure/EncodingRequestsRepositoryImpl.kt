package infrastructure

import android.content.Context
import java.io.File

class EncodingRequestsRepositoryImpl : EncodingRequestsRepository {
    override fun saveRequest(request: String, context: Context) {
        val path = context.filesDir
        val dataFile = File(path, "data.txt")
        dataFile.appendText(request + '\n')
    }

    override fun getAllRequests(context: Context): Array<String> {
        TODO("Not yet implemented")
    }
}