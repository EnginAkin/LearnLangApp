package com.eng.learnlang.domain.model

data class Word(
    val id : Int?=null,
    val word : String?=null,
    val mean : String?=null,
    val examplaSenctence : List<Sentence> ? =null,
    val category: Category?=null,
    var verified: Boolean=false
)
