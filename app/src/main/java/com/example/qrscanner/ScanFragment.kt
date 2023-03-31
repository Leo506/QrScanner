package com.example.qrscanner

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.qrscanner.databinding.FragmentScanBinding
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import java.util.*

class ScanFragment : Fragment(), AdapterView.OnItemSelectedListener {

    companion object {
        @JvmStatic
        fun newInstance() = ScanFragment();
    }

    private lateinit var bindings: FragmentScanBinding
    private lateinit var permissionLauncher: ActivityResultLauncher<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindings = FragmentScanBinding.inflate(inflater)
        bindings.startScanning.setOnClickListener(::onStartScanningButtonClick)

        val barcodeLauncher = registerForActivityResult(ScanContract()) { onScanningResult(it) }
        setupLanguagesSpinner()

        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            barcodeLauncher.launch(ScanOptions())
        }

        return bindings.root;
    }

    private fun onScanningResult(result: ScanIntentResult) {
        Log.i(ScanFragment::class.simpleName, "Scanned result: $result")
        if (result.contents == null) return

        val intent = Intent(this.activity, ScanResultActivity::class.java)
        intent.putExtra(SCAN_RESULT_KEY, result.contents.toString())

        val currentLocale = resources.configuration.locales.get(0)
        intent.putExtra(LOCALE_KEY, currentLocale.toLanguageTag())

        Log.i(ScanFragment::class.simpleName, "Starting ${ScanResultActivity::class.simpleName}")
        startActivity(intent)
    }

    private fun setupLanguagesSpinner() {
        Log.i(ScanFragment::class.simpleName, "Setup languages spinner")
        val spinner = bindings.langugesSpinner
        ArrayAdapter
            .createFromResource(this.requireContext(), R.array.languages_array, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
        spinner.onItemSelectedListener = this
    }

    private fun onStartScanningButtonClick(view: View) =
        permissionLauncher.launch(android.Manifest.permission.CAMERA)

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
        when (pos) {
            0 -> changeLanguage("en")
            1 -> changeLanguage("ru")
            else -> changeLanguage("en")
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun changeLanguage(languageTag: String) {
        Log.i(ScanFragment::class.simpleName, "Change language to $languageTag")
        val locale = Locale(languageTag.lowercase())
        val res = resources
        val dm = res.displayMetrics
        val config = res.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, dm)

        updateResources()
    }

    private fun updateResources() {
        bindings.welcomeText.setText(R.string.welcome_text)
        bindings.startScanning.setText(R.string.scan)
    }
}