package com.eng.learnlang.presentation.library_main_feed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.eng.learnlang.domain.model.LibraryBook
import com.eng.learnlang.presentation.components.CategoryPost
import com.eng.learnlang.presentation.components.LibraryPost
import com.eng.learnlang.presentation.components.ScaffolForComp
import com.eng.learnlang.presentation.components.StandartToolBar
import com.eng.learnlang.presentation.library_details.LibraryDetailScreen
import com.eng.learnlang.presentation.util.Screen

@Composable
fun LibraryMainFeedScreen(
    navController: NavController,
    listLibraryBook: List<LibraryBook> = listOf(
        LibraryBook(
            "Engin Akin",
            bookName = "Harikalar Diyarı",
            "Hikayeler pek çok konu üzerine yazılabilir. Günümüzde gerek kısa olmaları, gerekse hayata dair olmaları sebebi ile okuyucular tarafından çokça tercih edilmektedir.",
            " Hintli bir adam suyun içinde zar zor ilerlemeye çalışıyormuş. Bu sırada yanına bir Akrep yanaşmış. Adam akrebi kurtarmak istemiş ve parmağını ona doğru uzatmış. Fakat akrep adamın bu hamlesinden sonra adamı sokmuş. Hintli adam bu duruma çok şaşırmış fakat yine de tekrardan parmağını akrebe doğru uzatmış. Akrep tekrar adamın parmağını sokmuş.\n" +
                    "\n" +
                    " Bu olayı gören başka bir adam kendisini sürekli sokan bu akrebi kurtarmaktan vazgeçmesi gerektiğini söylemiş. Hintli adam buna şu şekilde cevap vermiş:"
        , readTime = "2 Dk"), LibraryBook(
            "Atakan Çağlar",
            bookName = "Yedi Cuceler",
            "Hikayeler pek çok konu üzerine yazılabilir. Günümüzde gerek kısa olmaları, gerekse hayata dair olmaları sebebi ile okuyucular tarafından çokça tercih edilmektedir.",
            " Hintli bir adam suyun içinde zar zor ilerlemeye çalışıyormuş. Bu sırada yanına bir Akrep yanaşmış. Adam akrebi kurtarmak istemiş ve parmağını ona doğru uzatmış. Fakat akrep adamın bu hamlesinden sonra adamı sokmuş. Hintli adam bu duruma çok şaşırmış fakat yine de tekrardan parmağını akrebe doğru uzatmış. Akrep tekrar adamın parmağını sokmuş.\n" +
                    "\n" +
                    " Bu olayı gören başka bir adam kendisini sürekli sokan bu akrebi kurtarmaktan vazgeçmesi gerektiğini söylemiş. Hintli adam buna şu şekilde cevap vermiş:"
                   , readTime = "5 Dk"), LibraryBook(
            "Hüseyin Uçar",
            bookName = "Adaletin Peşinde",
            "Hikayeler pek çok konu üzerine yazılabilir. Günümüzde gerek kısa olmaları, gerekse hayata dair olmaları sebebi ile okuyucular tarafından çokça tercih edilmektedir.",
            " Hintli bir adam suyun içinde zar zor ilerlemeye çalışıyormuş. Bu sırada yanına bir Akrep yanaşmış. Adam akrebi kurtarmak istemiş ve parmağını ona doğru uzatmış. Fakat akrep adamın bu hamlesinden sonra adamı sokmuş. Hintli adam bu duruma çok şaşırmış fakat yine de tekrardan parmağını akrebe doğru uzatmış. Akrep tekrar adamın parmağını sokmuş.\n" +
                    "\n" +
                    " Bu olayı gören başka bir adam kendisini sürekli sokan bu akrebi kurtarmaktan vazgeçmesi gerektiğini söylemiş. Hintli adam buna şu şekilde cevap vermiş:"
       , readTime = "12 Dk" ),
        LibraryBook(
            "Engin Akin",
            bookName = "Harikalar Diyarı",
            "Hikayeler pek çok konu üzerine yazılabilir. Günümüzde gerek kısa olmaları, gerekse hayata dair olmaları sebebi ile okuyucular tarafından çokça tercih edilmektedir.",
            " Hintli bir adam suyun içinde zar zor ilerlemeye çalışıyormuş. Bu sırada yanına bir Akrep yanaşmış. Adam akrebi kurtarmak istemiş ve parmağını ona doğru uzatmış. Fakat akrep adamın bu hamlesinden sonra adamı sokmuş. Hintli adam bu duruma çok şaşırmış fakat yine de tekrardan parmağını akrebe doğru uzatmış. Akrep tekrar adamın parmağını sokmuş.\n" +
                    "\n" +
                    " Bu olayı gören başka bir adam kendisini sürekli sokan bu akrebi kurtarmaktan vazgeçmesi gerektiğini söylemiş. Hintli adam buna şu şekilde cevap vermiş:"
            , readTime = "2 Dk"), LibraryBook(
            "Atakan Çağlar",
            bookName = "Yedi Cuceler",
            "Hikayeler pek çok konu üzerine yazılabilir. Günümüzde gerek kısa olmaları, gerekse hayata dair olmaları sebebi ile okuyucular tarafından çokça tercih edilmektedir.",
            " Hintli bir adam suyun içinde zar zor ilerlemeye çalışıyormuş. Bu sırada yanına bir Akrep yanaşmış. Adam akrebi kurtarmak istemiş ve parmağını ona doğru uzatmış. Fakat akrep adamın bu hamlesinden sonra adamı sokmuş. Hintli adam bu duruma çok şaşırmış fakat yine de tekrardan parmağını akrebe doğru uzatmış. Akrep tekrar adamın parmağını sokmuş.\n" +
                    "\n" +
                    " Bu olayı gören başka bir adam kendisini sürekli sokan bu akrebi kurtarmaktan vazgeçmesi gerektiğini söylemiş. Hintli adam buna şu şekilde cevap vermiş:"
            , readTime = "5 Dk"), LibraryBook(
            "Hüseyin Uçar",
            bookName = "Adaletin Peşinde",
            "Hikayeler pek çok konu üzerine yazılabilir. Günümüzde gerek kısa olmaları, gerekse hayata dair olmaları sebebi ile okuyucular tarafından çokça tercih edilmektedir.",
            " Hintli bir adam suyun içinde zar zor ilerlemeye çalışıyormuş. Bu sırada yanına bir Akrep yanaşmış. Adam akrebi kurtarmak istemiş ve parmağını ona doğru uzatmış. Fakat akrep adamın bu hamlesinden sonra adamı sokmuş. Hintli adam bu duruma çok şaşırmış fakat yine de tekrardan parmağını akrebe doğru uzatmış. Akrep tekrar adamın parmağını sokmuş.\n" +
                    "\n" +
                    " Bu olayı gören başka bir adam kendisini sürekli sokan bu akrebi kurtarmaktan vazgeçmesi gerektiğini söylemiş. Hintli adam buna şu şekilde cevap vermiş:"
            , readTime = "12 Dk" )
    )
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        StandartToolBar(
            navController = navController,
            title = {
                Text(text = "Library", fontWeight = FontWeight.Bold, color = Color.White)
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

        LazyColumn(modifier = Modifier.fillMaxSize().padding(bottom = 50.dp)){
            listLibraryBook.forEachIndexed { index, book ->
                item {
                    LibraryPost(
                        book,
                        onClick = {
                            navController.navigate(Screen.LibraryDetailScreen.route)
                    })
                }
            }
        }

    }


}