package com.eng.learnlang.feature_auth.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.eng.learnlang.core.util.Screen
import com.eng.learnlang.feature_auth.domain.model.AuthErrors
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel= hiltViewModel(),
    scaffoldState: ScaffoldState
) {
    val emailState =viewModel.emailState.value
    val passwordState =viewModel.passwordState.value
    val state=viewModel.loginState.value

LaunchedEffect(key1 = true ){
    viewModel.eventFlow.collectLatest { event ->
        when(event){
            is LoginViewModel.UiEvent.SnackbarEvent ->{
                scaffoldState.snackbarHostState.showSnackbar(message = event.message,duration = SnackbarDuration.Short)
            }
            is LoginViewModel.UiEvent.Navigate ->{
                scaffoldState.snackbarHostState.showSnackbar(message = "Giriş Başarılı Yönlendiriliyorsunuz....",duration = SnackbarDuration.Short)
                navController.navigate(event.route)
            }
        }

    }
}

    Box(modifier = Modifier.fillMaxSize()){
        Column (verticalArrangement = Arrangement.Center,modifier = Modifier
            .fillMaxSize()
            .padding(
                start = SpaceLarge,
                end = SpaceLarge,
                top = SpaceLarge,
                bottom = SpaceLarge,
            )
            .align(
                Alignment.Center
            )){
            Text(text = "Login", style = MaterialTheme.typography.h1)
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandartTextField(
                text = emailState.text,
                onValueChange = {
                   viewModel.onEvent(LoginEvent.EnteredEmail(it))
                },
                keyboardType = KeyboardType.Email,
                hint = "Email Or Username",
                error =when(emailState.error){
                    is AuthErrors.FieldEmpty ->{
                        "Email Cannot be empty"
                    }
                    else -> { ""}
                }
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandartTextField(
                text = passwordState.text,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.EnteredPassword(it))
                },
                error = when(passwordState.error){
                    is AuthErrors.FieldEmpty ->{
                        "Password Cannot be empty"
                    }
                    else -> { ""}
                } ,
                hint = "Password",
                keyboardType = KeyboardType.Password,
                showPasswordToggle = state.isPasswordVisible,
                onPasswordToggleClick = {
                    viewModel.onEvent(LoginEvent.TogglePasswordVisibility)
                }
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(modifier=Modifier.align(Alignment.End),onClick = {
                viewModel.onEvent(LoginEvent.Login)
            }) {
                Text(
                    text = "Login",
                    color= Color.Black

                )
            }
        }
        Text(text = buildAnnotatedString {
            append("dont have an account yet ?")
            append(" ")
            withStyle(style = SpanStyle(
                color = MaterialTheme.colors.primary,
                textDecoration = TextDecoration.Underline
            )
            ){
                append("Sign up")
            }

        },style = MaterialTheme.typography.body1,modifier= Modifier
            .align(Alignment.BottomCenter)
            .padding(vertical = 20.dp)
            .clickable { navController.navigate(Screen.RegisterScreen.route) })


    }


}