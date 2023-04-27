package com.example.qrscanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.qrscanner.databinding.FragmentEncodingHistoryBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import viewModels.EncodingHistoryViewModel


class EncodingHistoryFragment : Fragment() {

    private lateinit var binding: FragmentEncodingHistoryBinding
    private val vm: EncodingHistoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEncodingHistoryBinding.inflate(inflater)

        vm.encodingRequestsList.observe(requireActivity()) {
            binding.requestsList.adapter = EncodingRequestsAdapter(requireActivity(), it)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = EncodingHistoryFragment()
    }
}