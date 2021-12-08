package com.eng.learnlang.feature_auth.domain.use_case

import com.eng.learnlang.core.domain.util.ValidationUtil
import com.eng.learnlang.feature_auth.domain.model.AuthErrors
import com.eng.learnlang.feature_auth.domain.model.LoginResult
import com.eng.learnlang.feature_auth.domain.model.RegisterResult
import com.eng.learnlang.feature_auth.domain.repository.AuthRepository

class LoginUseCase(
     private val repository: AuthRepository
 ){
    suspend operator fun invoke(email : String,password : String) : LoginResult{

        val emailError= if(email.isBlank())AuthErrors.FieldEmpty else null;
        val passwordError= if(password.isBlank())AuthErrors.FieldEmpty else null;

        if(emailError!=null ||passwordError!=null){
            return LoginResult(
                emailError = emailError,
                passswordError = passwordError,
            )
        }
        var result = repository.login(email, password)
        return LoginResult(
            result = result
        )
    }
}
