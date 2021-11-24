package com.eng.learnlang.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.eng.learnlang.domain.model.Category
import com.eng.learnlang.presentation.ui.theme.MediumGray
import com.eng.learnlang.presentation.ui.theme.SpaceMedium
import com.eng.learnlang.presentation.ui.theme.hintgray
import com.eng.learnlang.util.Constants

@Composable
fun CategoryPost(
    category: Category
) {

       Box(
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 20.dp,vertical = 15.dp)
               .clip(RoundedCornerShape(20.dp))
               .background(hintgray)

               .border(width = 1.dp, color = Color.White, RoundedCornerShape(20.dp))
       ){

           Column (modifier = Modifier
               .fillMaxWidth()
               .padding(
                   start = 15.dp,
                   top = 7.dp,
                   end = 15.dp,
                   bottom = 15.dp
               )

           ){
               Text(text =category.categoryName , style = MaterialTheme.typography.h4,modifier = Modifier.align(CenterHorizontally))
               Spacer(modifier = Modifier.height(SpaceMedium))
               Text(
                   text = buildAnnotatedString {
                       append(category.categoryDescription)
                       withStyle(SpanStyle(
                           color = hintgray
                       )){
                           append(
                               "Read more ..."
                           )
                       }
                   },
                   style = MaterialTheme.typography.body2,
                   overflow = TextOverflow.Ellipsis,
                   maxLines =Constants.MAX_CATEGORY_DESCRİPTİON_LINE
               )
               Spacer(modifier = Modifier.height(SpaceMedium))

               Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                   Row(){

                       Icon(imageVector = Icons.Default.GridOn, contentDescription ="grid" ,tint = MaterialTheme.colors.primary)
                       Spacer(modifier = Modifier.width(5.dp))
                       Text(text = if(category.wordCount>0) "${category.wordCount} Word" else "",style = MaterialTheme.typography.body2,modifier = Modifier.padding(top = 2.dp),fontWeight = FontWeight.Bold, )
                   }
                   Row(){
                       Icon(imageVector = Icons.Filled.Timer, contentDescription ="timelapse 3x1" ,tint = MaterialTheme.colors.primary)
                       Spacer(modifier = Modifier.width(5.dp))
                       Text(text = if(category.dayCount>0) "${category.dayCount} Day" else "",style = MaterialTheme.typography.body2,modifier = Modifier.padding(top = 2.dp),fontWeight = FontWeight.Bold, )
                   }

               }
           }

       }


}
/*
Box(modifier = Modifier
        .fillMaxWidth()
        .padding(
            SpaceMedium
        )
        .shadow(2.dp)

    ){

       Column (modifier = Modifier
           .fillMaxWidth()
           .padding(8.dp)
           .background(MediumGray)
           .clip(MaterialTheme.shapes.large)
       ){
           Text(text =category.categoryName , style = MaterialTheme.typography.h3,modifier = Modifier.align(CenterHorizontally))
           Spacer(modifier = Modifier.height(SpaceMedium))
           Text(
               text = buildAnnotatedString {
                   append(category.categoryDescription)
                   withStyle(SpanStyle(
                       color = hintgray
                   )){
                       append(
                           "Read more ..."
                       )
                   }
               },
               style = MaterialTheme.typography.body2,
               overflow = TextOverflow.Ellipsis,
               maxLines =Constants.MAX_CATEGORY_DESCRİPTİON_LINE
           )
           Spacer(modifier = Modifier.height(SpaceMedium))

           Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                Row(){

                   Icon(imageVector = Icons.Default.GridOn, contentDescription ="grid" ,tint = MaterialTheme.colors.primary)
                   Spacer(modifier = Modifier.width(5.dp))
                   Text(text = if(category.wordCount>0) "${category.wordCount} Word" else "",style = MaterialTheme.typography.body2)
               }
               Row(){
                   Icon(imageVector = Icons.Filled.Timer, contentDescription ="timelapse 3x1" ,tint = MaterialTheme.colors.primary)
                   Spacer(modifier = Modifier.width(5.dp))
                   Text(text = if(category.dayCount>0) "${category.dayCount} Day" else "",style = MaterialTheme.typography.body2)
               }

           }
       }
    }
 */