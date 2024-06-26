package com.example.chatterboticaapp.di

import android.app.Application
import com.example.chatterboticaapp.data.model.ResponseInterceptor
import com.example.chatterboticaapp.data.remote.OpenAIApi
import com.example.chatterboticaapp.data.remote.PlayHTApiService
import com.example.chatterboticaapp.data.repository.GeminiAiRepositoryImpl
import com.example.chatterboticaapp.data.repository.OpenAIApiImpl
import com.example.chatterboticaapp.data.repository.PlayHTRepositoryImpl
import com.example.chatterboticaapp.domain.repository.GeminiAiRepository
import com.example.chatterboticaapp.domain.repository.OpenAIApiRepository
import com.example.chatterboticaapp.domain.repository.PlayHTRepository
import com.example.chatterboticaapp.ui.viewmodel.STTViewModel
import com.example.chatterboticaapp.ui.viewmodel.TTSViewModel
import com.example.chatterboticaapp.utils.VoiceToTextParser
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = ResponseInterceptor()
        return OkHttpClient.Builder()
            .addInterceptor(interceptor) // Tambahkan Interceptor ke OkHttpClient
            .build()
    }
//    fun provideOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder().build()
//    }

    @Provides
    @Singleton
    fun providePlayHTRetrofit(okHttpClient: OkHttpClient): Retrofit {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl("https://play.ht/api/v2/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


    @Provides
    @Singleton
    fun providePlayHTRepository(apiService: PlayHTApiService): PlayHTRepository {
        return PlayHTRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providePlayHTApiService(retrofit: Retrofit): PlayHTApiService {
        return retrofit.create(PlayHTApiService::class.java)
    }

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
    fun provideGeminiAiRepository() : GeminiAiRepository {
        return GeminiAiRepositoryImpl();
    }
    @Provides
    @Singleton
    fun provideVoiceToTextParser(appContext: Application): VoiceToTextParser {
        return VoiceToTextParser(appContext)
    }

    @Provides
    @Singleton
    fun provideSpeechListeningViewModel(voiceToTextParser: VoiceToTextParser): STTViewModel {
        return STTViewModel(voiceToTextParser)
    }

    @Provides
    @Singleton
    fun provideTTSViewModel(appContext: Application, playHTRepository: PlayHTRepository): TTSViewModel {
        return TTSViewModel(playHTRepository, appContext)
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