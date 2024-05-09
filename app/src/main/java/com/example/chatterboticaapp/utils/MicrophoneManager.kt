package com.example.chatterboticaapp.utils

import android.content.Context
import android.media.AudioManager

class MicrophoneManager(private val context: Context) {

    private val audioManager: AudioManager by lazy {
        context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    }

    // Mengecek apakah mikrofon dimatikan
    fun isMicrophoneMuted(): Boolean {
        return audioManager.isMicrophoneMute
    }

    // Mengatur status mikrofon
    fun setMicrophoneMute(mute: Boolean) {
        audioManager.isMicrophoneMute = mute
    }
}
