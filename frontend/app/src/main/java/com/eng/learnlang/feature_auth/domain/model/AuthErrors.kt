package com.eng.learnlang.feature_auth.domain.model

import com.eng.learnlang.core.util.Error
import com.eng.learnlang.feature_auth.presentation.register.RegisterState

sealed class AuthErrors : Error(){
    object FieldEmpty : AuthErrors()
    object InputTooShort: AuthErrors()
    object InvalidEmail : AuthErrors()
    object InvalidPassword : AuthErrors()
}
