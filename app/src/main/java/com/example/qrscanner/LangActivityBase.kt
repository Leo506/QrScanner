package com.example.qrscanner

import androidx.appcompat.app.AppCompatActivity
import java.util.*

open class LangActivityBase: AppCompatActivity() {

    protected fun changeLanguage(languageTag: String, updateResources: () -> Unit) {
        val locale = Locale(languageTag.lowercase())
        val res = resources
        val dm = res.displayMetrics
        val config = res.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, dm)

        updateResources()
    }

}