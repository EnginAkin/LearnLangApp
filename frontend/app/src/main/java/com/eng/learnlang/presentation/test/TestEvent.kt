package com.eng.learnlang.presentation.test

import com.eng.learnlang.domain.model.Word

sealed class TestEvent{
    data class ClickedButtonA(val value : Word): TestEvent()
    data class ClickedButtonB(val value : Word): TestEvent()
    data class ClickedButtonC(val value : Word): TestEvent()
    data class ClickedButtonD(val value : Word): TestEvent()
}
