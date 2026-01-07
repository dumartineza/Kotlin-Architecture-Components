package com.xcaret.android_kotlin_module

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.xcaret.android_kotlin_module.network.Connectivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            isAppearanceLightStatusBars = false
        }
        setContentView(R.layout.activity_main)
        Connectivity.registerNetworkCallback(this)
    }

    override fun onDestroy() {
        Connectivity.unRegisterNetworkCallback()
        super.onDestroy()
    }
}
