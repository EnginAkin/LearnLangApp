package com.eng.learnlang.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eng.learnlang.R
import com.eng.learnlang.presentation.components.ScaffolForComp
import com.eng.learnlang.presentation.components.StandartToolBar
import com.eng.learnlang.presentation.util.Screen

@Composable
fun ProfileScreen(
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        StandartToolBar(navController = navController, showBackArrow = false) {
            Text(text = "Profile", color = Color.White, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(10.dp))//
        Text(
            text = "Learn Lang Uygulamasını puanlayın.", color = Color.White, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Divider(color = MaterialTheme.colors.primary, thickness = 1.dp)
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(modifier = Modifier.fillMaxWidth(0.6f)) {
                    for (i in 1..5) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Star",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
                Row(Modifier.fillMaxWidth(0.4f)) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ,verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Image(
                            painter = painterResource(R.drawable.logo_lang),
                            contentDescription = "logo",
                            modifier = Modifier.size(50.dp)
                        )

                    }

                }

            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Sosyal Medya Hesaplarımız", color = Color.White, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Divider(color = MaterialTheme.colors.primary, thickness = 1.dp)
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {

            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.padding(vertical = 15.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = "facebook",
                        tint = MaterialTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "Facebook", color = Color.White)

                }
                Row(modifier = Modifier.padding(vertical = 15.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_twitter),
                        contentDescription = "Twitter",
                        tint = MaterialTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "Twitter", color = Color.White)

                }
                Row(modifier = Modifier.padding(vertical = 15.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_instagram),
                        contentDescription = "Instagram",
                        tint = MaterialTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "Instagram", color = Color.White)

                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Hesap Bilgileri",
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Divider(color = MaterialTheme.colors.primary, thickness = 1.dp)
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)) {
                Column() {
                    Text(text = "Ad", color = Color.White)
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "Engin", color = Color.White)
                }
                Spacer(modifier = Modifier.width(40.dp))


                Column() {
                    Text(text = "Gmail", color = Color.White)
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "engin.muh@gmail.com", color = Color.White)
                }


        }
        Spacer(modifier = Modifier.height(15.dp))
        Divider(color = MaterialTheme.colors.primary, thickness = 1.dp)
        Spacer(modifier = Modifier.height(15.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { /*TODO*/ },modifier = Modifier.fillMaxWidth()) {
                Text(text = "Istatistiklerimi Gör",color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { /*TODO*/ },modifier = Modifier.fillMaxWidth()) {
                Text(text = "Çıkış Yap",color = Color.Red)
            }
        }
    }
}