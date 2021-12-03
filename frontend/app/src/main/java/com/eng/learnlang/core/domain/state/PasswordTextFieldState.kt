package com.eng.learnlang.core.domain.state

import com.eng.learnlang.core.util.Error

data class PasswordTextFieldState(
    val text :String="",
    val error:Error?=null,
    val isVisible: Boolean=false

)