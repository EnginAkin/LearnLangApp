package com.eng.learnlang.core.domain.util

import android.util.Patterns
import com.eng.learnlang.core.util.Constants
import com.eng.learnlang.feature_auth.domain.model.AuthErrors

object ValidationUtil {
    fun validateEmail(email :String): AuthErrors?{
        val trimmedEmail = email.trim()
        if (trimmedEmail.isBlank()) {
            return AuthErrors.FieldEmpty
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(trimmedEmail).matches()) {
            return AuthErrors.InvalidEmail

        }

        return null
    }
    fun validateUsername(username: String):AuthErrors?{
        val trimmedUsername = username.trim()
        if (trimmedUsername.isBlank()) {
            return AuthErrors.FieldEmpty

        }
        if (trimmedUsername.length < Constants.MIN_USERNAME_LENGTH) {
           return AuthErrors.InputTooShort

        }

        return null
    }
    fun validatePassword(password :String): AuthErrors?{
        val trimmedPassword = password.trim()
        if (trimmedPassword.isBlank()) {
            return  AuthErrors.FieldEmpty

        }
        val capitalLetterInPassword = trimmedPassword.any { it.isUpperCase() }
        val numberInPassword = trimmedPassword.any { it.isDigit() }
        if (!capitalLetterInPassword || !numberInPassword) {
            return AuthErrors.InvalidPassword

        }
        if (trimmedPassword.length < Constants.MIN_PASSWORD_LENGTH) {
            return  AuthErrors.InputTooShort

        }

        return null
    }
}