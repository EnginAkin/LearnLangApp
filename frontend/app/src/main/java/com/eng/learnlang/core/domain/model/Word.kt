package com.eng.learnlang.core.domain.model

data class Word(
    val id : Int?=null,
    val word : String?=null,
    val mean : String?=null,
    val description : String?=null,
    val examplaSenctence : List<Sentence> ? =null,
    val category: Category?=null,
    var verified: Boolean=false
)
