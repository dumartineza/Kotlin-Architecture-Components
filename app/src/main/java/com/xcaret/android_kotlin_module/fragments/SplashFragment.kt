package com.xcaret.android_kotlin_module.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.xcaret.android_kotlin_module.R
import com.xcaret.android_kotlin_module.base.BaseFragment
import com.xcaret.android_kotlin_module.viewmodels.SessionManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment() {

    override fun setContentView(container: ViewGroup?): View {
        return LayoutInflater.from(context).inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewFragmentCreated(view: View, savedInstanceState: Bundle?) {
        fullScreen()
        lifecycleScope.launch { waitAndChangeScreen() }
    }

    private suspend fun waitAndChangeScreen() {
        delay(2500)

        val sessionManager = SessionManager(requireContext())
        if (sessionManager.isLoggedIn()) {
            val bundle = bundleOf(HAS_TOOLBAR_KEY to true)
            findNavController().navigate(R.id.action_splashFragment_to_mainFragment, bundle)
        } else {
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }
}