package com.eng.learnlang.feature_auth.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eng.learnlang.core.presentation.util.UiEvent
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.core.util.Screen
import com.eng.learnlang.feature_auth.domain.use_case.AuthenticateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authenticateUseCase: AuthenticateUseCase,
) :ViewModel(){
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()
    init {
        viewModelScope.launch {
            val result= authenticateUseCase()
            when(result){
                is Resource.Success ->{
                _eventFlow.emit(
                    UiEvent.Navigate(Screen.TopicWordScreen.route)
                )
                }
                is Resource.Error ->{
                    _eventFlow.emit(
                        UiEvent.Navigate(Screen.LoginScreen.route)
                    )
                }
            }
        }

    }
}