package viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import infrastructure.EncodingRequestsRepository

class EncodingHistoryViewModel(private val requestsRepository: EncodingRequestsRepository)
    : ViewModel() {

    private val mutableEncodingRequestsList = MutableLiveData<List<String>>()
    val encodingRequestsList = mutableEncodingRequestsList

    init {
        mutableEncodingRequestsList.value = requestsRepository.getAllRequests()
    }
}