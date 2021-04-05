package com.example.logincodedemo.ui.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.example.logincodedemo.data.model.LoggedInUserRespons

/**
 * User details post authentication that is exposed to the UI
 */
data class UserLoggedIn(val displayName: MutableLiveData<LoggedInUserRespons>) {
    companion object {
        private var INSTANCE: UserLoggedIn? = null

        fun getInstance(name: MutableLiveData<LoggedInUserRespons> = MutableLiveData()): UserLoggedIn = INSTANCE ?: synchronized(this) {
            INSTANCE ?: UserLoggedIn(name).also { INSTANCE = it }
        }

        fun validatePassword(password: String): Boolean {
            password?.let {
                val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,20}$"
                val passwordMatcher = Regex(passwordPattern)

                return passwordMatcher.find(password) != null
            } ?: return false
        }

        fun isUserNameValid(username: String): Boolean {
            return if (!username.contains('@')) {
                Patterns.EMAIL_ADDRESS.matcher(username).matches()
            } else {
                username.isNotBlank()
            }
        }
    }
}