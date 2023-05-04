package com.example.qrscanner

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.example.qrscanner.databinding.FragmentEncodingHistoryBinding
import models.EncodingRequest
import org.koin.androidx.viewmodel.ext.android.viewModel
import viewModels.EncodingHistoryViewModel


class EncodingHistoryFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = EncodingHistoryFragment()
    }

    private lateinit var binding: FragmentEncodingHistoryBinding
    private val vm: EncodingHistoryViewModel by viewModel()
    private var actionMode: ActionMode? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEncodingHistoryBinding.inflate(inflater)

        vm.encodingRequestsList.observe(requireActivity()) {
            binding.requestsList.adapter = EncodingRequestsAdapter(requireActivity(), it)
        }

        binding.requestsList.setOnItemLongClickListener { adapterView, _, position, _ ->

            when (val selectedItem = adapterView.adapter.getItem(position)) {
                is EncodingRequest -> {
                    showActionMode(selectedItem.requestText, selectedItem.requestDate.toLongDateString())
                    true
                }
                else -> false
            }
        }
        return binding.root
    }

    private fun showActionMode(title: String, subtitle: String) {
        if (actionMode == null) {
            actionMode = requireActivity().startActionMode(object : ActionMode.Callback {
                override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    mode?.menuInflater?.inflate(R.menu.history_actions_menu, menu)
                    return true
                }

                override fun onPrepareActionMode(p0: ActionMode?, p1: Menu?): Boolean {
                    return false
                }

                override fun onActionItemClicked(mode: ActionMode?, menuItem: MenuItem?): Boolean {
                    val selectedItem = binding.requestsList.selectedItem
                    if (selectedItem is EncodingRequest)
                        Log.i("SelectedItem", "Item: ${selectedItem.requestText}")
                    mode?.finish()
                    return false
                }

                override fun onDestroyActionMode(p0: ActionMode?) {
                    actionMode = null
                }

            })
            actionMode?.title = title
            actionMode?.subtitle = subtitle
        }
        else {
            actionMode?.finish()
        }
    }
}