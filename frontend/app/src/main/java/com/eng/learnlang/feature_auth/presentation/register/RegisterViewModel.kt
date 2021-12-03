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
                validateUsername(usernameState.value.text)
                validateEmail(emailState.value.text)
                validatePassoword(passwordState.value.text)
                register()
            }
        }
    }
    private fun register(){
        if(emailState.value.error !=null || usernameState.value.error !=null || passwordState.value.error !=null){
            return
        }
        viewModelScope.launch {
            _registerState.value= RegisterState(isLoading = true)
            val result= registerUseCase(
                email = emailState.value.text,
                username = usernameState.value.text,
                password = passwordState.value.text
            )
            when(result){
                is Resource.Error ->{
                    result.message?.let { message ->
                        _eventFlow.emit(UiEvent.SnackbarEvent(message))
                        _registerState.value=RegisterState(isLoading = false)
                    }
                }
                is Resource.Success ->{
            _eventFlow.emit(UiEvent.SnackbarEvent("Kayıt Başarılı şekilde tamamlandı. Giriş yap sayfasından giriş yapabilirsiniz"))
                _registerState.value=RegisterState(isLoading = false)
                }

            }

        }
    }
    private fun validateUsername(value: String) {
        val trimmedUsername = value.trim()
        if (trimmedUsername.isBlank()) {
            _usernameState.value = _usernameState.value.copy(
                error = AuthErrors.FieldEmpty
            )
            return
        }
        if (trimmedUsername.length < MIN_USERNAME_LENGTH) {
            _usernameState.value = _usernameState.value.copy(
                error = AuthErrors.InputTooShort
            )
            return
        }
        _usernameState.value = _usernameState.value.copy(
            error = null
        )
    }

    private fun validateEmail(value: String) {
        val trimmedEmail = value.trim()
        if (trimmedEmail.isBlank()) {
            _emailState.value = _emailState.value.copy(
                error = AuthErrors.FieldEmpty
            )
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            _emailState.value = _emailState.value.copy(
                error = AuthErrors.InvalidEmail
            )
            return
        }
        _emailState.value = _emailState.value.copy(
            error = null
        )
    }

    private fun validatePassoword(value: String) {
        if (value.isBlank()) {
            _passwordState.value = _passwordState.value.copy(
                error = AuthErrors.FieldEmpty
            )
            return
        }
        if (value.length < MIN_PASSWORD_LENGTH) {
            _passwordState.value = _passwordState.value.copy(
                error = AuthErrors.InputTooShort
            )
            return
        }
        val capitalLetterInPassword = value.any { it.isUpperCase() }
        val numberInPassword = value.any { it.isDigit() }
        if (!capitalLetterInPassword || !numberInPassword) {
            _passwordState.value = _passwordState.value.copy(
                error = AuthErrors.InvalidPassword
            )
            return
        }
        _passwordState.value = _passwordState.value.copy(
            error = null
        )
    }


    sealed class UiEvent{
        data class  SnackbarEvent(val message : String): UiEvent()
    }
}