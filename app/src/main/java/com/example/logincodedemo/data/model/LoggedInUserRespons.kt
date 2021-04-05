package com.example.logincodedemo.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUserRespons(
        val isSuccessful: Boolean,
        val username: String
)