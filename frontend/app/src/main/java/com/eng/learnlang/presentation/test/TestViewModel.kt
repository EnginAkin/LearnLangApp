package com.eng.learnlang.presentation.test

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(): ViewModel(){


    fun onEvent(event: TestEvent){
            when(event){

            }
    }
}
