package com.eng.learnlang.feature_auth.presentation.login

import com.eng.learnlang.feature_auth.presentation.register.RegisterEvent

sealed class LoginEvent{
    data class EnteredEmail(val email :String):LoginEvent()
    data class EnteredPassword(val password :String):LoginEvent()
    object TogglePasswordVisibility : LoginEvent()
    object Login: LoginEvent()
}
