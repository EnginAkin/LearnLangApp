package com.eng.learnlang.feature_library.domain

data class ResponseYandexTranslateApi(
    val code: String ?= null,
    val lang: String?=null,
    val text: String?=null
)
