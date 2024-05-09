package com.example.chatterboticaapp.utils

import android.content.Context
import android.media.AudioManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
class MicrophoneUtils (@ApplicationContext private val context: Context) {

    private val audioManager: AudioManager =
        context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    fun isMicrophoneMuted(): Boolean {
        return audioManager.isMicrophoneMute
    }

    fun setMicrophoneMute(mute: Boolean) {
        audioManager.isMicrophoneMute = mute
    }
}
