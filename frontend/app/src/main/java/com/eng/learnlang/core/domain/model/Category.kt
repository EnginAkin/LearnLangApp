package com.eng.learnlang.core.domain.model

data class Category(
    val categoryName: String,
    val categoryDescription:String,
    val wordCount: Int ,
    val dayCount: Int
)