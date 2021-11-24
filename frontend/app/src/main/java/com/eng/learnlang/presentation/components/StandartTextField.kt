package com.eng.learnlang.presentation.components

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.style.TextAlign

@Composable
fun StandartTextField(
    modifier: Modifier=Modifier,
    text: String = "",
    hint : String ="",
    error: String="",
    showPasswordToggle:Boolean=false,
    onPasswordToggleClick : (Boolean) ->Unit = {},
    keyboardType: KeyboardType= KeyboardType.Text,
    onValueChange: (String) ->Unit
) {
    val isPasswordToggleDisplayed by remember{
        mutableStateOf(keyboardType== KeyboardType.Password)
    }

    Column(modifier = modifier.fillMaxWidth()) {
        TextField(
            value = text,
            onValueChange = {
                onValueChange(it)
            },
            placeholder = {
                Text(text = hint,style = MaterialTheme.typography.body1)
            },
            isError = error!="",
            visualTransformation = if(!showPasswordToggle && isPasswordToggleDisplayed)PasswordVisualTransformation() else VisualTransformation.None,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType=keyboardType
            ),
            trailingIcon = {
                if(isPasswordToggleDisplayed){
                    IconButton(onClick = { onPasswordToggleClick(!showPasswordToggle) }) {
                        Icon(
                            imageVector = if (showPasswordToggle) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = "Eye Icon"
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

    }
    if(error.isNotEmpty()){
        Text(text = error,style = MaterialTheme.typography.body2,color = MaterialTheme.colors.error,textAlign = TextAlign.End,modifier=Modifier.fillMaxWidth())
    }
    

}