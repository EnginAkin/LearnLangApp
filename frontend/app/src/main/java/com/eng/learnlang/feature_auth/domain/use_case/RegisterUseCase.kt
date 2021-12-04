package com.eng.learnlang.feature_auth.domain.use_case

import android.util.Patterns
import com.eng.learnlang.core.domain.util.ValidationUtil
import com.eng.learnlang.core.util.Constants
import com.eng.learnlang.core.util.SimpleResource
import com.eng.learnlang.feature_auth.domain.model.AuthErrors
import com.eng.learnlang.feature_auth.domain.model.RegisterResult
import com.eng.learnlang.feature_auth.domain.repository.AuthRepository

class RegisterUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        username: String,
        password: String
    ): RegisterResult {
        val emailError=ValidationUtil.validateEmail(email)
        val passwordError=ValidationUtil.validatePassword(password)
        val usernameError=ValidationUtil.validateUsername(username)

        if(emailError!=null || usernameError!=null ||passwordError!=null){
            return RegisterResult(
                emailError = emailError,
                usernameError = usernameError,
                passswordError = passwordError,
)
        }
        var result = repository.register(email, username, password)



        return RegisterResult(
            emailError = emailError,
            usernameError = usernameError,
            passswordError = passwordError,
            result = result
        )


    }

}