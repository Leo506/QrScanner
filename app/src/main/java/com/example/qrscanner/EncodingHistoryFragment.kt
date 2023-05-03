package com.example.qrscanner

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.example.qrscanner.databinding.FragmentEncodingHistoryBinding
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
    ): View? {
        binding = FragmentEncodingHistoryBinding.inflate(inflater)

        vm.encodingRequestsList.observe(requireActivity()) {
            binding.requestsList.adapter = EncodingRequestsAdapter(requireActivity(), it)
            binding.requestsList.onItemLongClickListener = object : AdapterView.OnItemLongClickListener {
                override fun onItemLongClick(
                    p0: AdapterView<*>?,
                    p1: View?,
                    p2: Int,
                    p3: Long
                ): Boolean {
                    showActionMode()
                    return true
                }

            }
        }

        return binding.root
    }

    private fun showActionMode() {
        if (actionMode == null) {
            actionMode = requireActivity().startActionMode(object : ActionMode.Callback {
                override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    mode?.menuInflater?.inflate(R.menu.history_actions_menu, menu)
                    return true
                }

                override fun onPrepareActionMode(p0: ActionMode?, p1: Menu?): Boolean {
                    return false
                }

                override fun onActionItemClicked(p0: ActionMode?, p1: MenuItem?): Boolean {
                    Log.i("ActionModeTest", "Items selected ${p1?.itemId} ${p1?.title}")
                    actionMode?.finish()
                    return false
                }

                override fun onDestroyActionMode(p0: ActionMode?) {
                    actionMode = null
                }

            })
        }
        else {
            actionMode?.finish()
        }
    }
}