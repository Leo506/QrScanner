package com.example.qrscanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.qrscanner.databinding.FragmentEncodingHistoryBinding
import infrastructure.EncodingRequestsRepository
import org.koin.android.ext.android.inject


class EncodingHistoryFragment : Fragment() {

    private lateinit var binding: FragmentEncodingHistoryBinding
    private val repository: EncodingRequestsRepository by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEncodingHistoryBinding.inflate(inflater)

        binding.requestsList.adapter = EncodingRequestsAdapter(requireActivity(),
            repository.getAllRequests(requireContext()).reversed())

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = EncodingHistoryFragment()
    }
}