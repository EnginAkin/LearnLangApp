package com.eng.learnlang.presentation.login

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eng.learnlang.presentation.components.StandartTextField
import com.eng.learnlang.presentation.ui.theme.SpaceLarge
import com.eng.learnlang.presentation.ui.theme.SpaceMedium
import com.eng.learnlang.presentation.util.Screen

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel= hiltViewModel(),
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
            Text(text = "Login", style = MaterialTheme.typography.h1)
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandartTextField(
                text = viewModel.usernameText.value,
                onValueChange = {
                    viewModel.setUsernameText(it)
                },
                hint = "Email Or Username",
                error = viewModel.usernameError.value
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandartTextField(
                text = viewModel.passwordText.value,
                onValueChange = {
                    viewModel.setpasswordText(it)
                },
                error = viewModel.passwordError.value ,
                hint = "Password",
                keyboardType = KeyboardType.Password,
                showPasswordToggle = viewModel.showPassword.value,
                onPasswordToggleClick = {
                    viewModel.setShowPassword(it)
                }
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(modifier=Modifier.align(Alignment.End),onClick = {
                navController.navigate(Screen.TopicWordScreen.route)
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