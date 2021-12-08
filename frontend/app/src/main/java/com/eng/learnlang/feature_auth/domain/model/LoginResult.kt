package com.eng.learnlang.feature_auth.domain.model

import com.eng.learnlang.core.util.SimpleResource

data class LoginResult(
    val emailError : AuthErrors ?=null,
    val passswordError : AuthErrors ?=null,
    val result: SimpleResource?=null
)
