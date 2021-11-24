package com.eng.learnlang.presentation.register

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
import com.eng.learnlang.presentation.components.StandartTextField
import com.eng.learnlang.presentation.login.LoginViewModel
import com.eng.learnlang.presentation.ui.theme.SpaceLarge
import com.eng.learnlang.presentation.ui.theme.SpaceMedium
import com.eng.learnlang.presentation.util.Screen


@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel:RegisterViewModel= hiltViewModel()
) {
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
            Text(text = "Register", style = MaterialTheme.typography.h1)
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandartTextField(
                text = viewModel.usernameText.value,
                onValueChange = {
                    viewModel.setUsernameText(it)
                },
                hint = "Username",
                error = viewModel.usernameError.value,

            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandartTextField(
                text = viewModel.emailText.value,
                onValueChange = {
                    viewModel.setemailText(it)
                },
                hint = "E-Mail",
                error = viewModel.emailError.value,

                )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandartTextField(
                text = viewModel.passwordText.value,
                onValueChange = {
                    viewModel.setpasswordText(it)
                },
                hint = "Password",
                error = viewModel.passwordError.value,
                keyboardType = KeyboardType.Password
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(modifier=Modifier.align(Alignment.End),onClick = {

            }) {
                Text(
                    text = "Register",
                    color= Color.Black

                )
            }
        }
        Text(text = buildAnnotatedString {
            append("Already have an account ?")
            append(" ")
            withStyle(style = SpanStyle(
                color = MaterialTheme.colors.primary,
                textDecoration = TextDecoration.Underline
            )
            ){
                append("Sign In")
            }

        },style = MaterialTheme.typography.body1,modifier= Modifier
            .align(Alignment.BottomCenter)
            .padding(vertical = 20.dp)
            .clickable { navController.popBackStack()})


    }


}