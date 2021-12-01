package com.eng.learnlang.domain.model

data class LibraryBook(
    val authorName: String ? = null,
    val bookName: String ? = null,
    val bookDescription : String ? = null,
    val content: String? = null,
    val readTime : String ?=null
)
