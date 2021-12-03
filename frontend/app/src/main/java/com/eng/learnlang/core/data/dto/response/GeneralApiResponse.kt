package com.eng.learnlang.core.data.dto.response

data class GeneralApiResponse(
    val message :String?=null,
    val successful: Boolean,
    val data :Any ? =null
    )
