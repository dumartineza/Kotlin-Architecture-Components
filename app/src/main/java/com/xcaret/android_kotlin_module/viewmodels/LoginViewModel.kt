package com.xcaret.android_kotlin_module.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.xcaret.android_kotlin_module.models.User
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class LoginViewModel : ViewModel() {

    var onErrorOccurred: ((property: KProperty<*>, oldValue: String?, newValue: String?) -> Unit)? = null
    private var usernameError: String? by Delegates.observable(null) { property: KProperty<*>, oldValue: String?, newValue: String? ->
        onErrorOccurred?.invoke(property, oldValue, newValue)
    }
    private var passwordError: String? by Delegates.observable(null) { property: KProperty<*>, oldValue: String?, newValue: String? ->
        onErrorOccurred?.invoke(property, oldValue, newValue)
    }

    lateinit var user: User

    fun login(user: User, isValidLogin: (source: Boolean) -> Unit) {
        if (validateFields(user.username, user.password)) {
            isValidLogin(credentialsAreOk(user.username, user.password))
        } else {
            isValidLogin(false)
        }
    }

    private fun validateFields(username: String, password: String): Boolean {
        var isValidated = true
        if (username.isEmpty()) {
            isValidated = false
            usernameError = "The username could not be empty"
        }
        if (password.isEmpty()) {
            isValidated = false
            passwordError = "The password could not be empty"
        } else if (password.length < 6) {
            isValidated = false
            passwordError = "The password must have at least 6 characters"
        }
        return isValidated

    }

    private fun credentialsAreOk(username: String, password: String): Boolean =
        username.equals("d", true) && password.equals("xcaret123", true)
}

class SessionManager(context: Context) {

    private val prefs = context.getSharedPreferences(
        "app_prefs",
        Context.MODE_PRIVATE
    )

    fun saveLogin() {
        prefs.edit()
            .putBoolean("is_logged_in", true)
            .apply()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean("is_logged_in", false)
    }

    fun logout() {
        prefs.edit().clear().apply()
    }
}