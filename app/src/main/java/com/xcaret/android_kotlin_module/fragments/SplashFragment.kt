package com.xcaret.android_kotlin_module.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.xcaret.android_kotlin_module.R
import com.xcaret.android_kotlin_module.base.BaseFragment

class SplashFragment : BaseFragment() {

    override fun setContentView(container: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewFragmentCreated(view: View, savedInstanceState: Bundle?) {
        fullScreen()
        startTimer()
    }

    private fun startTimer() {
        object: Thread() {
            override fun run() {
                try {
                    sleep(2500)
                } catch (e: Exception) {
                    Log.e(TAG, "Something went wrong.")
                } finally {
                    view?.post { findNavController().navigate(R.id.loginFragment) }
                }
            }
        }.start()
    }

}