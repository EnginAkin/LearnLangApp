package com.eng.learnlang.feature_main.presentation.TopicWordDetailsScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopicWordDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel(){

    init {
        savedStateHandle.get<String>("categoryName")?.let {

        }
    }
}