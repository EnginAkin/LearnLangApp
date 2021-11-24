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

    fun setUsernameText(username : String){
        _usernameText.value=username
    }
    fun setpasswordText(password : String){
        _passwordText.value=password
    }
    fun setemailText(emailText : String){
        _emailText.value=emailText
    }

}