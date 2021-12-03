package com.eng.learnlang.feature_test.presentation.test

import com.eng.learnlang.core.domain.model.Word

sealed class TestEvent{
    data class ClickedButtonA(val value : Word): TestEvent()
    data class ClickedButtonB(val value : Word): TestEvent()
    data class ClickedButtonC(val value : Word): TestEvent()
    data class ClickedButtonD(val value : Word): TestEvent()
}
