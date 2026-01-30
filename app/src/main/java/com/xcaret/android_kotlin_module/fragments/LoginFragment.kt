package com.xcaret.android_kotlin_module.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.xcaret.android_kotlin_module.R
import com.xcaret.android_kotlin_module.base.BaseFragment
import com.xcaret.android_kotlin_module.databinding.FragmentLoginBinding
import com.xcaret.android_kotlin_module.dialogs.SimpleProgressDialog
import com.xcaret.android_kotlin_module.models.User
import com.xcaret.android_kotlin_module.viewmodels.LoginViewModel
import com.xcaret.android_kotlin_module.viewmodels.SessionManager
import kotlinx.coroutines.delay

class LoginFragment : BaseFragment() {

    private val viewModel: LoginViewModel by viewModels()
    private var dialog: SimpleProgressDialog? = null
    private var binding: FragmentLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = SimpleProgressDialog()
    }

    override fun setContentView(container: ViewGroup?): View =
        FragmentLoginBinding.inflate(layoutInflater).apply {
            binding = this
        }.root

    override fun onViewFragmentCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.onErrorOccurred = { property, old, new ->
            Log.d("LogProperty", "$new - $old")
            if (property.name == "usernameError") {
                binding?.UsernameEditText?.error = new
            } else {
                binding?.PasswordEditText?.error = new
            }
        }

        binding?.buttonLogin?.setOnClickListener {
            dialog?.show(requireActivity().supportFragmentManager, "ProgressDialog")
            viewModel.user = User(
                username = binding?.UsernameEditText?.text.toString(),
                password = binding?.PasswordEditText?.text.toString()
            )
            viewModel.login(viewModel.user) { credentialsAreOk ->
                if (credentialsAreOk) {
                    SessionManager(requireContext()).saveLogin()
                    goToMain()
                } else {
                    showBadCredentialsDialog()
                    dialog?.dismiss()
                }
            }
        }

        onBackPressed {
            activity?.finish()
        }
    }

    private fun showBadCredentialsDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.error_bad_credentials_title))
            .setMessage(getString(R.string.error_bad_credentials_message))
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }

    private fun goToMain() {
        object : Thread() {
            override fun run() {
                try {
                    sleep(2000)
                } catch (e: Exception) {
                    throw IllegalAccessError("Something went wrong")
                } finally {
                    dialog?.dismiss()
                    val bundle = bundleOf(HAS_TOOLBAR_KEY to true)
                    view?.post { findNavController().navigate(R.id.mainFragment, bundle) }
                }
            }
        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}