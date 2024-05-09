package com.example.chatterboticaapp.di

import android.app.Application
import android.content.Context
import com.example.chatterboticaapp.data.remote.OpenAIApi
import com.example.chatterboticaapp.data.repository.OpenAIApiImpl
import com.example.chatterboticaapp.domain.repository.OpenAIApiRepository
import com.example.chatterboticaapp.ui.viewmodel.SpeechListeningViewModel
import com.example.chatterboticaapp.utils.MicrophoneUtils
import com.example.chatterboticaapp.utils.VoiceToTextParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Singleton
//    @Provides
//    fun provideMicrophoneUtils(@ApplicationContext context: Context): MicrophoneUtils {
//        return MicrophoneUtils(context)
//    }

    @Provides
    @Singleton
    // Scope, how many this dependencies we actually have per component
    // If its singleton then we only have one single instance throughout the while life time of the application
    // If we don't have @Singleton, everytime we inject OpenAIApi interface, the instance will be made
    // In this case we have 2 repository which need OpenAIApi interface, so the instance is 2, they both having whole app lifespan
    fun provideOpenAIApi(): OpenAIApi {
        return Retrofit.Builder()
            // change with OpenAI api
            .baseUrl("https://test.com")
            .build()
            .create(OpenAIApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOpenAIApiRepository(api : OpenAIApi, appContext: Application) : OpenAIApiRepository{
        return OpenAIApiImpl(api, appContext)
    }

    @Provides
    @Singleton
    fun provideVoiceToTextParser(appContext: Application): VoiceToTextParser {
        return VoiceToTextParser(appContext)
    }

    @Provides
    @Singleton
    fun provideSpeechListeningViewModel(voiceToTextParser: VoiceToTextParser): SpeechListeningViewModel {
        return SpeechListeningViewModel(voiceToTextParser)
    }

    @Provides
    @Singleton
    @Named("hello1")
    fun provideString1() = "Hello 1"

    @Provides
    @Singleton
    @Named("hello2")
    fun provideString2() = "Hello 2"
}