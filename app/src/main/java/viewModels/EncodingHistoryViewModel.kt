package viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import infrastructure.EncodingRequestsRepository
import models.EncodingRequest

class EncodingHistoryViewModel(private val requestsRepository: EncodingRequestsRepository)
    : ViewModel() {

    private val mutableEncodingRequestsList = MutableLiveData<List<EncodingRequest>>()
    val encodingRequestsList = mutableEncodingRequestsList

    init {
        mutableEncodingRequestsList.value = requestsRepository.getAllRequests()
    }
}