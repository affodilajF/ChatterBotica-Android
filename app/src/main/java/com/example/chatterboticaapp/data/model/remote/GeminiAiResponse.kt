package com.example.chatterboticaapp.data.model.remote

import java.util.UUID

data class GeminiAiResponse(
    var id: String = UUID.randomUUID().toString(),
    var request: String,
    var response: String,
)

