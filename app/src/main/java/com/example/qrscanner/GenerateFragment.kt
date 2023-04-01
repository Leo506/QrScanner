package com.example.qrscanner

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.qrscanner.databinding.FragmentGenerateBinding
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class GenerateFragment : Fragment() {

    private lateinit var bindings: FragmentGenerateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindings = FragmentGenerateBinding.inflate(inflater)

        bindings.generateButton.setOnClickListener {
            val input = bindings.contentInput.text.toString()
            if  (input.isEmpty()) {
                AlertDialog.Builder(this@GenerateFragment.requireContext()).apply {
                    setTitle("Error")
                    setMessage("Input can not be empty")
                }.show()
            } else {
                Log.i(
                    GenerateFragment::class.simpleName,
                    "Generating new qr code for input: $input"
                )
                val encoder = BarcodeEncoder()
                val bitmap = encoder.encodeBitmap(
                    input,
                    BarcodeFormat.QR_CODE, bindings.qrCodeImage.width, bindings.qrCodeImage.height
                )
                bindings.qrCodeImage.setImageBitmap(bitmap)
                bindings.qrCodeImage.visibility = VISIBLE

                val inputManager =
                    requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
            }
        }

        return bindings.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = GenerateFragment();
    }
}