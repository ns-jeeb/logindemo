package com.example.logincodedemo.ui.login

import android.util.Patterns
import androidx.lifecycle.*
import com.example.logincodedemo.data.LoginRepository
import com.example.logincodedemo.R

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _isloginSuccess = MutableLiveData<Boolean>()
    val isLoginSuccess: LiveData<Boolean> = _isloginSuccess

    fun login(username: String, password: String) {
        val result = loginRepository.login(username, password)

        if (result != null) {
            UserLoggedIn.getInstance(result)
            _isloginSuccess.value = true
        } else {
            _isloginSuccess.value = false
        }

    }

    fun loginDataChanged(username: String, password: String) {
        if (!UserLoggedIn.isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!UserLoggedIn.validatePassword(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

}