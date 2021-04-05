package com.example.logincodedemo.ui.login

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class UserLoggedInTest {
    /**
     * the input is not valid if :
     * ther user name and the password is empty
     * the user name is more than 2 character
     *
     */
    @Test
    fun validate_password_true() {
        val result = UserLoggedIn.validatePassword( "@1Sad")
        assertThat(result).isTrue()

    }
    @Test
    fun validate_password_false() {
        val result = UserLoggedIn.validatePassword( "abc1")
        assertThat(result).isFalse()
    }
    @Test
    fun validate_password_false_empty() {
        val result = UserLoggedIn.validatePassword( "")
        assertThat(result).isFalse()
    }
}