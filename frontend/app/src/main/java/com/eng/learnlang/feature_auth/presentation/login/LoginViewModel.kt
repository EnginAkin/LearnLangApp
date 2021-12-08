package com.eng.learnlang.feature_auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eng.learnlang.core.domain.state.PasswordTextFieldState
import com.eng.learnlang.core.domain.state.StandartTextFiedlState
import com.eng.learnlang.core.presentation.util.UiEvent
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.core.util.Screen
import com.eng.learnlang.feature_auth.domain.use_case.LoginUseCase
import com.eng.learnlang.feature_auth.presentation.register.RegisterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _emailState = mutableStateOf(StandartTextFiedlState())
    val emailState: State<StandartTextFiedlState> = _emailState

    private val _passwordState = mutableStateOf(PasswordTextFieldState())
    val passwordState: State<PasswordTextFieldState> = _passwordState


    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow=_eventFlow.asSharedFlow()

    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.EnteredEmail ->{
                _emailState.value=_emailState.value.copy(
                text = event.email
            )
            }
            is LoginEvent.TogglePasswordVisibility ->{
                _loginState.value=_loginState.value.copy(
                    isPasswordVisible = !_loginState.value.isPasswordVisible
                )
            }
            is LoginEvent.EnteredPassword ->{
                _passwordState.value=_passwordState.value.copy(
                    text = event.password
                )
            }
            is LoginEvent.Login ->{
                _loginState.value=_loginState.value.copy(isLoading = true)
               viewModelScope.launch {
                   val result =loginUseCase(
                       email = emailState.value.text,
                       password = passwordState.value.text
                   )
                   _loginState.value=_loginState.value.copy(isLoading = false)
                   if(result.emailError !=null){
                       _emailState.value=_emailState.value.copy(
                           error = result.emailError
                       )
                   }
                   if(result.passswordError !=null){
                       _passwordState.value=_passwordState.value.copy(
                           error = result.passswordError
                       )
                   }
                   when(result.result){

                       is Resource.Success ->{
                           _eventFlow.emit(
                               UiEvent.Navigate(Screen.TopicWordScreen.route)
                           )
                       }
                       is Resource.Error ->{
                           result.result.message?.let {
                               _eventFlow.emit(
                                   UiEvent.SnackbarEvent(result.result.message)
                               )

                           }?: _eventFlow.emit(
                               UiEvent.SnackbarEvent("Kullanıcı adı veya şifre hatalıdır")                           )
                       }
                       null ->{

                       }
                   }
               }
            }
        }
    }



}