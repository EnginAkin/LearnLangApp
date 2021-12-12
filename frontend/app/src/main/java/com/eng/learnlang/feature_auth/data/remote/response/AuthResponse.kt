package com.eng.learnlang.feature_auth.data.remote.response

import com.eng.learnlang.feature_auth.domain.model.UserInfo

data class AuthResponse(
    val userInfo: UserInfo?=null,
    val successful : Boolean,
    val message : String?=null,
)
