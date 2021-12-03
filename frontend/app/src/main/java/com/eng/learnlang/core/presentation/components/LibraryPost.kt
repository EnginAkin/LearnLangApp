package com.eng.learnlang.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.eng.learnlang.R
import com.eng.learnlang.core.domain.model.LibraryBook
import com.eng.learnlang.core.presentation.ui.theme.SpaceMedium
import com.eng.learnlang.core.presentation.ui.theme.hintgray
import com.eng.learnlang.core.util.Constants

@Composable
fun LibraryPost(
    libraryBook: LibraryBook,
    modifier: Modifier= Modifier,
    onClick : () -> Unit = {},
    ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 15.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(hintgray)
            .border(width = 1.dp, color = Color.White, RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                }
                .padding(
                    start = 15.dp,
                    top = 7.dp,
                    end = 15.dp,
                    bottom = 15.dp
                )
        ) {
            libraryBook.bookName?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            Spacer(modifier = Modifier.height(SpaceMedium))
            Text(
                text = buildAnnotatedString {
                    libraryBook.bookDescription?.let { append(it) }
                    withStyle(
                        SpanStyle(
                            color = hintgray
                        )
                    ) {
                        append(
                            "Read more ..."
                        )
                    }
                },
                style = MaterialTheme.typography.body2,
                overflow = TextOverflow.Ellipsis,
                maxLines = Constants.MAX_CATEGORY_DESCRİPTİON_LINE
            )
            Spacer(modifier = Modifier.height(SpaceMedium))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row() {

                    Icon(
                        painter = painterResource(id =R.drawable.ic_grid),
                        contentDescription = "grid",
                        tint = MaterialTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = if (!libraryBook.readTime.isNullOrBlank()) "${libraryBook.authorName}" else "",
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(top = 2.dp),
                        fontWeight = FontWeight.Bold,
                    )
                }
                Row() {
                    Icon(
                        imageVector = Icons.Filled.Timer,
                        contentDescription = "timelapse 3x1",
                        tint = MaterialTheme.colors.primary
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = if (!libraryBook.readTime.isNullOrBlank()) "${libraryBook.readTime}" else "",
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(top = 2.dp),
                        fontWeight = FontWeight.Bold,
                    )
                }

            }
        }
    }
}