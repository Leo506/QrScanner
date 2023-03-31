package com.example.qrscanner

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import com.example.qrscanner.databinding.ActivityScanResultBinding
import java.util.*

class ScanResultActivity : LangActivityBase() {
    private lateinit var bindings: ActivityScanResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityScanResultBinding.inflate(layoutInflater);
        setContentView(bindings.root)

        supportActionBar?.hide()

        val result = intent.getStringExtra(SCAN_RESULT_KEY)
        bindings.resultText.text = result

        val languageCode = intent.getStringExtra(LOCALE_KEY) ?: return
        changeLanguage(languageCode) { updateResources() }

        if (isUri(result)) {
            bindings.openBrowserButton.visibility = VISIBLE
            bindings.openBrowserButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(result))
                startActivity(intent)
            }
        }
    }

    private fun updateResources() {
        bindings.resultTitle.setText(R.string.result)
        bindings.openBrowserButton.setText(R.string.open_in_browser)
    }

    private fun isUri(result: String?): Boolean =
        (result != null) && (result.startsWith("http") or result.startsWith("https"))

    fun onBackPressed(view: View) = finish()
}