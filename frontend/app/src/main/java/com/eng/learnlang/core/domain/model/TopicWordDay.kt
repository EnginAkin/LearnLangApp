package com.eng.learnlang.core.domain.model

data class TopicWordDay(
    var categoryName :String="",
    var wordDayCount: Int=0 ,
    val totalWordCount : Int=10,
    var learnedCountWord :Int=0,
)
