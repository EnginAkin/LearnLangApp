package com.eng.learnlang.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {
    private val _usernameText = mutableStateOf("")
    val usernameText: State<String> = _usernameText

    private val _emailText = mutableStateOf("")
    val emailText: State<String> = _emailText

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText

    private val _usernameError = mutableStateOf("")
    val usernameError: State<String> = _usernameError

    private val _emailError= mutableStateOf("")
    val emailError: State<String> = _emailError

    private val _passwordError = mutableStateOf("")
    val passwordError: State<String> = _passwordError


    fun setUsernameText(username : String){
        _usernameText.value=username
    }
    fun setpasswordText(password : String){
        _passwordText.value=password
    }
    fun setemailText(emailText : String){
        _emailText.value=emailText
    }

    fun setUsernameError(usernameError : String){
        _usernameError.value=usernameError
    }
    fun setpasswordError(passwordError : String){
        _passwordError.value=passwordError
    }
    fun setEmailError(emailError : String){
        _emailError.value=emailError
    }

}