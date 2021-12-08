package com.eng.learnlang.feature_auth.presentation.login

data class LoginState(
    var isLoading : Boolean=false,
    val isPasswordVisible: Boolean=false
)
