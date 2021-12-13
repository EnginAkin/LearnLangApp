package com.eng.learnlang.feature_main.presentation.topic_word

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eng.learnlang.core.domain.state.PasswordTextFieldState
import com.eng.learnlang.core.presentation.util.UiEvent
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.feature_main.domain.use_case.GetCategoriesUseCase
import com.eng.learnlang.feature_main.domain.use_case.MainFeedUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicWordsViewModel @Inject constructor(
private val mainFeedUseCases: MainFeedUseCases
): ViewModel() {

    private val _state = mutableStateOf(TopicWordState())
    val state: State<TopicWordState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow=_eventFlow.asSharedFlow()


    init {
        loadTopicWords()
    }
    private fun loadTopicWords(){
        viewModelScope.launch {
            var result = mainFeedUseCases.getCategoriesUseCase()
            _state.value=state.value.copy(
                isLoading = true
            )
            when(result){
                is Resource.Success ->{
                    result.data?.let {
                        _state.value=state.value.copy(
                            categories = result.data!!
                        )
                    }
                    _state.value=state.value.copy(
                        isLoading = false
                    )
                }

                is Resource.Error ->{
                    _eventFlow.emit(
                        UiEvent.SnackbarEvent("Yüklerken Sorun Oluştu")
                    )
                }
            }
        }
    }

}