package com.example.qrscanner

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Bitmap
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
import infrastructure.EncodingRequestsRepository
import org.koin.android.ext.android.inject

class GenerateFragment : Fragment() {

    private lateinit var bindings: FragmentGenerateBinding
    private val encodingRequestsRepository: EncodingRequestsRepository by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindings = FragmentGenerateBinding.inflate(inflater)

        bindings.generateButton.setOnClickListener {
            val input = bindings.contentInput.text.toString()
            if  (input.isEmpty()) {
                showEmptyInputAlert()
            } else {
                Log.i(
                    GenerateFragment::class.simpleName,
                    "Generating new qr code for input: $input"
                )
                encodingRequestsRepository.saveRequest(input, this.requireContext())
                val bitmap = encode(input)
                setQrImage(bitmap)

                hideKeyboard()
            }
        }

        return bindings.root
    }

    private fun showEmptyInputAlert() {
        AlertDialog.Builder(this@GenerateFragment.requireContext()).apply {
            setTitle(getString(R.string.error))
            setMessage(R.string.input_can_not_be_empty)
        }.show()
    }

    private fun encode(input: String): Bitmap? {
        val encoder = BarcodeEncoder()
        return encoder.encodeBitmap(
            input,
            BarcodeFormat.QR_CODE, bindings.qrCodeImage.width, bindings.qrCodeImage.height
        )
    }

    private fun setQrImage(bitmap: Bitmap?) {
        bindings.qrCodeImage.setImageBitmap(bitmap)
        bindings.qrCodeImage.visibility = VISIBLE
    }

    private fun hideKeyboard() {
        val inputManager =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
    }

    companion object {
        @JvmStatic
        fun newInstance() = GenerateFragment()
    }
}