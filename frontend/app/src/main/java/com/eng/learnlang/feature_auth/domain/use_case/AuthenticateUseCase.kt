package com.eng.learnlang.feature_auth.domain.use_case

import com.eng.learnlang.core.util.SimpleResource
import com.eng.learnlang.feature_auth.domain.repository.AuthRepository

class AuthenticateUseCase (private val repository: AuthRepository){
    suspend operator fun invoke():SimpleResource{
        return repository.authenticate()
    }
}