package com.example.chatterboticaapp.data.model.remote

//data class TextToSpeechRequest(
//    val text: String,
//    val voice: String,
//    val output_format: String,
//    val speed: Int,
//    val sample_rate: Int,
//    val voice_engine: String
//)

import com.google.gson.annotations.SerializedName

data class PlayHTRequest(
    @SerializedName("text") var text: String,
    @SerializedName("voice") val voice: String = "s3://peregrine-voices/oliver_narrative2_parrot_saad/manifest.json",
    @SerializedName("voice_engine") val voice_engine: String = "PlayHT2.0"
)