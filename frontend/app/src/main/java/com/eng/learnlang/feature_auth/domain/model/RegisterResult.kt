package com.eng.learnlang.feature_auth.domain.model

import com.eng.learnlang.core.util.SimpleResource

data class RegisterResult(
    val usernameError : AuthErrors ?=null,
    val passswordError : AuthErrors ?=null,
    val emailError : AuthErrors ?=null,
    val result: SimpleResource?=null
)
