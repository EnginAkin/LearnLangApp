package com.eng.learnlang.domain.model

data class Word(
    val id : Int,
    val word : String,
    val mean : String,
    val examplaSenctence : List<Sentence>,
    val category: Category,
)
