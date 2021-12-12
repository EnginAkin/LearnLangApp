package com.eng.learnlang.feature_main.presentation.topic_word

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eng.learnlang.core.domain.model.Category
import com.eng.learnlang.core.presentation.components.CategoryPost
import com.eng.learnlang.core.presentation.components.StandartToolBar
import com.eng.learnlang.core.util.Screen

@Composable
fun TopicWords(
    navController: NavController,
    viewmodel:TopicWordsViewModel= hiltViewModel()
    ) {
    val state=viewmodel.state.value
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        StandartToolBar(
            navController = navController,
            title = {
                Text(text = "Word Categories", fontWeight = FontWeight.Bold, color = Color.White)
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = false,
            navAction = {
                IconButton(onClick = {
                    //navController.navigate(Screen.SearchScreen.route)
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "add",
                        tint = Color.White
                    )
                }
            }
        )

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp)){

            state.categories.forEachIndexed { index, category ->
                item {
                    CategoryPost(category = category) {
                        navController.navigate(Screen.TopicWordDetailScreen.route+"/${category.categoryName}")
                    }
                }
            }
        }

    }


}