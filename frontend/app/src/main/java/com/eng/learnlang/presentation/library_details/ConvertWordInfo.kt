package com.eng.learnlang.presentation.library_details

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eng.learnlang.util.speak
import androidx.compose.foundation.layout.R as R


@Composable
fun ConverWordInfo(
    word: String,
    mean: String,
    closeClicked: () -> Unit,
    onclick: () -> Unit
) {
    val context= LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.weight(2f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = { speak(word,context) }) {
                    Icon(
                        painter = painterResource(id = com.eng.learnlang.R.drawable.ic_ses_icon),
                        contentDescription = "icon ses",
                        Modifier.size(24.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.weight(6f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = word, color = Color.White)
            }
            Column(modifier = Modifier.weight(2f), horizontalAlignment = Alignment.End) {
                IconButton(onClick = {
                    closeClicked()
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "icon close",
                        Modifier.size(24.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = mean, color = Color.White)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = onclick) {
                Text(text = "Kelimelere Ekle", color = Color.White)
            }
        }
    }

}


