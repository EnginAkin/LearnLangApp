package com.eng.learnlang.feature_auth.domain.model

import com.eng.learnlang.core.util.Error
import com.eng.learnlang.feature_auth.presentation.register.RegisterState

sealed class AuthErrors : Error(){
    object FieldEmpty : Error()
    object InputTooShort: Error()
    object InvalidEmail : Error()
    object InvalidPassword : Error()
}
