package com.eng.learnlang.core.data.dto.response

data class GeneralApiResponse<T>(
    val message :String?=null,
    val successful: Boolean,
    val data :T ? =null
    )
