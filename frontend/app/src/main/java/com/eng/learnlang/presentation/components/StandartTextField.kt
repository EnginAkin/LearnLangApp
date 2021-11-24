package com.eng.learnlang.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun StandartTextField(
    text: String = "",
    hint : String ="",
    isError: Boolean=false,
    keyboardType: KeyboardType= KeyboardType.Text,
    onValueChange: (String) ->Unit
) {
    val isPasswordToggleDisplayed by remember{
        mutableStateOf(keyboardType== KeyboardType.Password)
    }
    var isPasswordVisible by remember{
        mutableStateOf(false)
    }
    
    TextField(
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = {
            Text(text = hint,style = MaterialTheme.typography.body1)
        },
        isError = isError,
        visualTransformation = if(!isPasswordVisible && isPasswordToggleDisplayed)PasswordVisualTransformation() else VisualTransformation.None,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
           keyboardType=keyboardType
        ),
        trailingIcon = {
            if(isPasswordToggleDisplayed){
                IconButton(onClick = { isPasswordVisible=!isPasswordVisible }) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = "Eye Icon"
                    )
                }
            }
        },
        modifier = Modifier.fillMaxWidth(),

    )
    
}