package viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import infrastructure.EncodingRequestsRepository
import models.EncodingRequest

class EncodingHistoryViewModel(private val requestsRepository: EncodingRequestsRepository)
    : ViewModel() {

    private val mutableEncodingRequestsList = MutableLiveData<List<EncodingRequest>>()
    val encodingRequestsList: LiveData<List<EncodingRequest>> = mutableEncodingRequestsList

    init {
        mutableEncodingRequestsList.value = requestsRepository.getAllRequests()
    }

    fun deleteRequest(request: EncodingRequest) {
        requestsRepository.delete(request)
        mutableEncodingRequestsList.value = requestsRepository.getAllRequests()
    }
}