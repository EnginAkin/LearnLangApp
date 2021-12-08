package com.eng.learnlang.feature_auth.domain.repository

import com.eng.learnlang.core.data.dto.response.GeneralApiResponse
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.core.util.SimpleResource
import com.eng.learnlang.feature_auth.data.remote.request.CreateAccountRequest

interface AuthRepository {

    suspend fun register(
        email : String,
        username : String,
        password :String
    ): SimpleResource

    suspend fun login(
        email : String,
        password :String
    ): SimpleResource
}