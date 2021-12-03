package com.eng.learnlang.feature_test.presentation.teach_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestDetailviewModel @Inject constructor(): ViewModel() {

    private val _currentIndexTeach = mutableStateOf(0)
    val currentIndexTeach: State<Int> = _currentIndexTeach


    fun setCurrenIndexTeach(index : Int){
        _currentIndexTeach.value=index

    }

}
