package com.example.chatterboticaapp


import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ChatterBoticaApplication : Application() {

    override fun onCreate() {
        super.onCreate()



        Log.d(TAG, "OnCreate is coming")
    }

    companion object {
        const val TAG = "Chatter Botica App"
    }

}