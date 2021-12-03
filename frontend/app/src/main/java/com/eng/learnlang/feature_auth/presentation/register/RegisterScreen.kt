package com.eng.learnlang.feature_auth.presentation.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.eng.learnlang.core.util.Constants.MIN_USERNAME_LENGTH


@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val state = viewModel.registerState.value
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
                text = state.usernameText,
                onValueChange = { username ->
                    viewModel.onEvent(RegisterEvent.EnteredUsername(username))
                },
                hint = "Username",
                keyboardType = KeyboardType.Text,
                error = when (state.usernameError) {
                    RegisterState.UsernameError.FieldEmpty -> {
                        "Username Cannot be null"
                    }
                    RegisterState.UsernameError.InputTooShort -> {
                        "Username is too short, min length is : "+MIN_USERNAME_LENGTH
                    }
                    null -> ""
                },

                )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandartTextField(
                text = state.emailText,
                onValueChange = { email ->
                    viewModel.onEvent(RegisterEvent.EnteredEmail(email))
                },
                keyboardType = KeyboardType.Email,
                hint = "E-Mail",
                error = when (state.emailError) {
                    RegisterState.EmailError.FieldEmpty ->{
                        "Field Cannot be null"
                    }
                    RegisterState.EmailError.InvalidEmail ->{
                        "This email is invalid"
                    }
                    null-> ""
                },

                )
            Spacer(modifier = Modifier.height(SpaceMedium))

            StandartTextField(
                text = state.passwordText,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredPassword(it))
                },
                hint = "Password",
                keyboardType = KeyboardType.Password,
                error = when (state.passwordError) {
                    is  RegisterState.PasswordError.FieldEmpty -> {
                        "Field Cannot be null"
                    }
                    is  RegisterState.PasswordError.InvalidPassword -> {
                        "Password needs to containt at least one upper case one digit"
                    }
                    is  RegisterState.PasswordError.InputTooShort -> {
                        "this input too short"
                    }
                    else -> ""
                },
                onPasswordToggleClick = {
                    viewModel.onEvent(RegisterEvent.TogglePasswordVisibility)
                },
                isPasswordToggleDisplayed = true,
                showPasswordToggle = state.isPasswordVisible
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(modifier = Modifier.align(Alignment.End), onClick = {
                viewModel.onEvent(RegisterEvent.Register)
            }) {
                Text(
                    text = "Register",
                    color = Color.Black

                )
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