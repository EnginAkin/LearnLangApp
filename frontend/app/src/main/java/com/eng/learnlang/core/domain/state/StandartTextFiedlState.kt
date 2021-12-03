package com.eng.learnlang.core.domain.state

import com.eng.learnlang.core.util.Error

data class StandartTextFiedlState(
    val text : String= "",
    val error : Error ?=null
)
