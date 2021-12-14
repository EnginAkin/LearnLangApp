package com.eng.learnlang.core.domain.model

data class Word(
    val id : Int?=null,
    val name : String?=null,
    val mean : String?=null,
    val description : String?=null,
    val exampleSentence : List<Sentence> ? =null,
    val category: Category?=null,
    var verified: Boolean=false,
    var isWordInMyWordList : Boolean = false
)

