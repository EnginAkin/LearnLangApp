package com.eng.learnlang.feature_auth.domain.use_case

import com.eng.learnlang.core.util.SimpleResource
import com.eng.learnlang.feature_auth.domain.repository.AuthRepository

class RegisterUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(
        email :String,
        username :String,
        password : String
    ):SimpleResource {
    return  repository.register(email,username,password)


    }

}