package com.example.qrscanner

import android.os.Bundle
import android.view.*
import android.widget.ListView
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

        binding.requestsList.choiceMode = ListView.CHOICE_MODE_SINGLE
        binding.requestsList.setOnItemLongClickListener { adapterView, _, position, _ ->

            when (val selectedItem = adapterView.adapter.getItem(position)) {
                is EncodingRequest -> {
                    binding.requestsList.clearFocus()
                    binding.requestsList.requestFocusFromTouch()
                    binding.requestsList.requestFocus()
                    binding.requestsList.setItemChecked(position, true)
                    showActionMode(selectedItem.requestText, selectedItem.requestDate.toLongDateString())
                    true
                }
                else -> false
            }
        }

        binding.requestsList.setOnItemClickListener { _, _, _, _ -> actionMode?.finish() }

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
                    val position = binding.requestsList.checkedItemPosition
                    val selectedItem = binding.requestsList.adapter.getItem(position)
                    if (selectedItem is EncodingRequest && menuItem?.itemId == R.id.delete_request) {
                        vm.deleteRequest(selectedItem)
                    }
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