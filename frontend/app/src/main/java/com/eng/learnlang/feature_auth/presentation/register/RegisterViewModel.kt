package com.eng.learnlang.feature_auth.presentation.register

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eng.learnlang.core.domain.state.PasswordTextFieldState
import com.eng.learnlang.core.domain.state.StandartTextFiedlState
import com.eng.learnlang.core.util.Constants.MIN_PASSWORD_LENGTH
import com.eng.learnlang.core.util.Constants.MIN_USERNAME_LENGTH
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.feature_auth.domain.model.AuthErrors
import com.eng.learnlang.feature_auth.domain.use_case.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {


    private val _usernameState = mutableStateOf(StandartTextFiedlState())
    val usernameState: State<StandartTextFiedlState> = _usernameState

    private val _emailState = mutableStateOf(StandartTextFiedlState())
    val emailState: State<StandartTextFiedlState> = _emailState

    private val _passwordState = mutableStateOf(PasswordTextFieldState())
    val passwordState: State<PasswordTextFieldState> = _passwordState

    private val _registerState = mutableStateOf(RegisterState())
    val registerState: State<RegisterState> = _registerState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow=_eventFlow.asSharedFlow()


    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EnteredUsername -> {
                _usernameState.value = _usernameState.value.copy(
                    text = event.username
                )
             }
            is RegisterEvent.TogglePasswordVisibility -> {
                _passwordState.value = _passwordState.value.copy(
                    isVisible = !_passwordState.value.isVisible
                )
            }
            is RegisterEvent.EnteredEmail -> {
                _emailState.value = _emailState.value.copy(
                    text = event.email
                )
            }
            is RegisterEvent.EnteredPassword -> {
                _passwordState.value = _passwordState.value.copy(
                    text = event.password
                )

            }
            is RegisterEvent.Register -> {
                register()
            }
        }
    }
    private fun register(){
        _registerState.value=RegisterState(isLoading = true)

        _usernameState.value= _usernameState.value.copy(
            error = null
        )// içerdeki tüm değerleri null a çevirir
        _emailState.value=_emailState.value.copy(
            error = null
        )
        _passwordState.value=_passwordState.value.copy(
            error = null
        )
        viewModelScope.launch {
            val registerResult= registerUseCase(
                email = emailState.value.text,
                username = usernameState.value.text,
                password = passwordState.value.text
            )
            if(registerResult.emailError!=null){
                _emailState.value=_emailState.value.copy(
                    error = registerResult.emailError
                )

            }
            if(registerResult.usernameError!=null){
                _usernameState.value=_usernameState.value.copy(
                    error = registerResult.usernameError
                )
            }
            if(registerResult.passswordError!=null){
                _passwordState.value=_passwordState.value.copy(
                    error = registerResult.passswordError
                )
            }
            when(registerResult.result){
                is Resource.Error ->{
                    registerResult.result.message?.let { message ->
                        _eventFlow.emit(UiEvent.SnackbarEvent(message))
                        _registerState.value=RegisterState(isLoading = false)
                    }
                }
                is Resource.Success ->{
            _eventFlow.emit(UiEvent.SnackbarEvent("Kayıt Başarılı şekilde tamamlandı. Giriş yap sayfasından giriş yapabilirsiniz"))
                    _registerState.value=RegisterState(isLoading = false)
                    _usernameState.value= StandartTextFiedlState()// içerdeki tüm değerleri null a çevirir
                    _emailState.value=StandartTextFiedlState()
                    _passwordState.value=PasswordTextFieldState()

                }
                null ->{
                    _registerState.value=RegisterState(isLoading = false)

                }

            }

        }
    }
    sealed class UiEvent{
        data class  SnackbarEvent(val message : String): UiEvent()
    }
}