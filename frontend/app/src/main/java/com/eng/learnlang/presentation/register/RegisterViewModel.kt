package com.eng.learnlang.presentation.register

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.eng.learnlang.util.Constants.MIN_PASSWORD_LENGTH
import com.eng.learnlang.util.Constants.MIN_USERNAME_LENGTH
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {
    private val _registerState = mutableStateOf(RegisterState())
    val registerState: State<RegisterState> = _registerState

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EnteredUsername -> {
                _registerState.value=_registerState.value.copy(
                    usernameText = event.username
                )
            }
            is RegisterEvent.TogglePasswordVisibility->{
                _registerState.value=_registerState.value.copy(
                    isPasswordVisible = !registerState.value.isPasswordVisible
                )
            }
            is RegisterEvent.EnteredEmail -> {
                _registerState.value=_registerState.value.copy(
                    emailText = event.email
                )            }
            is RegisterEvent.EnteredPassword -> {
                _registerState.value=_registerState.value.copy(
                    passwordText = event.password
                )}
            is RegisterEvent.Register -> {
                validateUsername(registerState.value.usernameText)
                validateEmail(registerState.value.emailText)
                validatePassoword(registerState.value.passwordText)

            }
        }
    }
    private fun validateUsername(value : String){
        val trimmedUsername = value.trim()
        if(trimmedUsername.isBlank()){
            _registerState.value=_registerState.value.copy(
                usernameError = RegisterState.UsernameError.FieldEmpty
            )
            return
        }
        if(trimmedUsername.length < MIN_USERNAME_LENGTH){
            _registerState.value=_registerState.value.copy(
                usernameError = RegisterState.UsernameError.InputTooShort
            )
            return
        }
        _registerState.value=_registerState.value.copy(
            usernameError = null
        )
    }
    private fun validateEmail(value : String){
        val trimmedEmail = value.trim()
        if(trimmedEmail.isBlank()){
            _registerState.value=_registerState.value.copy(
                emailError = RegisterState.EmailError.FieldEmpty
            )
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            _registerState.value=_registerState.value.copy(
                emailError = RegisterState.EmailError.InvalidEmail
            )
            return
        }
        _registerState.value=_registerState.value.copy(
            emailError = null
        )
    }
    private fun validatePassoword(value : String){
        val trimmedEmail = value.trim()
        if(trimmedEmail.isBlank()){
            _registerState.value=_registerState.value.copy(
                passwordError = RegisterState.PasswordError.FieldEmpty
            )
            return
        }
        if(value.length < MIN_PASSWORD_LENGTH){
            _registerState.value=_registerState.value.copy(
                passwordError = RegisterState.PasswordError.InputTooShort
            )
            return
        }
        val capitalLetterInPassword=value.any{it.isUpperCase()}
        val numberInPassword=value.any { it.isDigit() }
        if(!capitalLetterInPassword || !numberInPassword){
            _registerState.value=_registerState.value.copy(
                passwordError = RegisterState.PasswordError.InvalidPassword
            )
            return
        }
        _registerState.value=_registerState.value.copy(
            passwordError = null
        )
    }


}