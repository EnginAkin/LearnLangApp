package com.eng.learnlang.util

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

fun speak(text : String,applicationContext: Context){
    lateinit var tts : TextToSpeech
    tts = TextToSpeech(applicationContext, TextToSpeech.OnInitListener {
        if(it == TextToSpeech.SUCCESS){
            tts.language = Locale.US
            tts.setSpeechRate(1.0f)
            tts.speak(text, TextToSpeech.QUEUE_ADD,null)
        }
    })
}