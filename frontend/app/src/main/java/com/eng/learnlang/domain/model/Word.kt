package com.eng.learnlang.domain.model

data class Word(
    val id : Int?=null,
    val word : String,
    val mean : String,
    val examplaSenctence : List<Sentence> ? =null,
    val category: Category,
)
