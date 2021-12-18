package com.eng.learnlang.feature_test.presentation.test

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.eng.learnlang.feature_test.presentation.teach_detail.TeachState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(): ViewModel(){

    private val _state = mutableStateOf(TestState())
    val state: State<TestState> = _state

    fun onEvent(event: TestEvent){
            when(event){

            }
    }
}
