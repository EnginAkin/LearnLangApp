package com.eng.learnlang.feature_auth.presentation.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eng.learnlang.core.presentation.components.StandartTextField
import com.eng.learnlang.core.presentation.ui.theme.SpaceLarge
import com.eng.learnlang.core.presentation.ui.theme.SpaceMedium
import com.eng.learnlang.core.presentation.util.UiEvent
import com.eng.learnlang.core.util.Constants.MIN_USERNAME_LENGTH
import com.eng.learnlang.feature_auth.domain.model.AuthErrors
import kotlinx.coroutines.flow.collectLatest


@Composable
fun RegisterScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val usernameState= viewModel.usernameState.value
    val passwordState= viewModel.passwordState.value
    val emailState= viewModel.emailState.value
    val registerState= viewModel.registerState.value

    LaunchedEffect(key1 = true ){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is UiEvent.SnackbarEvent ->{
                    scaffoldState.snackbarHostState.showSnackbar(event.message, duration = SnackbarDuration.Short)
                }
            }

        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center, modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = SpaceLarge,
                    end = SpaceLarge,
                    top = SpaceLarge,
                    bottom = SpaceLarge,
                )
                .align(
                    Alignment.Center
                )
        ) {
            Text(text = "Register", style = MaterialTheme.typography.h1)
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandartTextField(
                text = usernameState.text,
                onValueChange = { username ->
                    viewModel.onEvent(RegisterEvent.EnteredUsername(username))
                },
                hint = "Username",
                keyboardType = KeyboardType.Text,
                error = when (usernameState.error) {
                    is AuthErrors.FieldEmpty -> {
                        "Username Cannot be null"
                    }
                    is AuthErrors.InputTooShort -> {
                        "Username is too short, min length is : "+MIN_USERNAME_LENGTH
                    }
                    else -> ""
                },

                )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandartTextField(
                text = emailState.text,
                onValueChange = { email ->
                    viewModel.onEvent(RegisterEvent.EnteredEmail(email))
                },
                keyboardType = KeyboardType.Email,
                hint = "E-Mail",
                error = when (emailState.error) {
                   is AuthErrors.FieldEmpty ->{
                        "Field Cannot be null"
                    }
                    is AuthErrors.InvalidEmail ->{
                        "This email is invalid"
                    }
                    else -> ""
                },

                )
            Spacer(modifier = Modifier.height(SpaceMedium))

            StandartTextField(
                text = passwordState.text,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredPassword(it))
                },
                hint = "Password",
                keyboardType = KeyboardType.Password,
                error = when (passwordState.error) {
                    is  AuthErrors.FieldEmpty -> {
                        "Field can't be null"
                    }
                    is  AuthErrors.InvalidPassword -> {
                        "Password needs to containt at least one upper case one digit"
                    }
                    is  AuthErrors.InputTooShort -> {
                        "this input too short"
                    }
                    else -> ""
                },
                onPasswordToggleClick = {
                    viewModel.onEvent(RegisterEvent.TogglePasswordVisibility)
                },
                isPasswordToggleDisplayed = true,
                showPasswordToggle = passwordState.isVisible
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(
                modifier = Modifier.align(Alignment.End),
                enabled = !registerState.isLoading,
                onClick = {
                    viewModel.onEvent(RegisterEvent.Register)
                }
            ) {
                Text(
                    text = "Register",
                    color = Color.Black

                )
            }
            if(registerState.isLoading){
                CircularProgressIndicator()
            }
        }
        Text(text = buildAnnotatedString {
            append("Already have an account ?")
            append(" ")
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colors.primary,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append("Sign In")
            }

        }, style = MaterialTheme.typography.body1, modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(vertical = 20.dp)
            .clickable { navController.popBackStack() })


    }


}