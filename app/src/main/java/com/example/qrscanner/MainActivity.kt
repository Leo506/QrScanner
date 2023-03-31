package com.example.qrscanner

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.qrscanner.databinding.ActivityMainBinding

class MainActivity : LangActivityBase() {

    private lateinit var bindings: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityMainBinding.inflate(layoutInflater);
        setContentView(bindings.root)
        supportActionBar?.hide()

        replaceFragment(ScanFragment.newInstance())

        bindings.mainNavigationMenu.setOnItemSelectedListener { menuItem ->
            Log.i(MainActivity::class.simpleName, "Bottom menu item was clicked: $menuItem")
            when (menuItem.itemId) {
                R.id.scan_page -> {
                    replaceFragment(ScanFragment.newInstance())
                    true
                }

                R.id.generate_page -> {
                    replaceFragment(GenerateFragment.newInstance())
                    true
                }

                else -> false
            }
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        Log.i(MainActivity::class.simpleName, "Replace fragment to ${fragment::class.simpleName}")
        supportFragmentManager
            .beginTransaction()
            .replace(bindings.fragmentsFrame.id, fragment)
            .commit()
    }
}