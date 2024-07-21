package com.example.chatterboticaapp.data.model.remote

import java.util.Date

data class Input(
    val output_format: String,
    val quality: String,
    val sample_rate: Int,
    val seed: Any?,
    val speed: Int,
    val temperature: Any?,
    val text: String,
    val voice: String
)

data class Output(
    val duration: Double,
    val size: Int,
    val url: String
)

data class Link(
    val contentType: String,
    val description: String,
    val href: String,
    val method: String,
    val rel: String
)

data class PlayHTResponse(
    val id: String,
    val created: Date,
    val input: Input,
    val status: String,
    val output: Output,
    val _links: List<Link>
)

