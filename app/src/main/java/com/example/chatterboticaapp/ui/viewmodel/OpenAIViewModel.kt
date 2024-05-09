package com.example.chatterboticaapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.chatterboticaapp.domain.repository.OpenAIApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


// Injecting OpenAIApiRepository
// The annotation is kinda "Hi please inject everything stated in constructor.
// Dagger will search everything in ModuleApp whether those is having instance
@HiltViewModel
class OpenAIViewModel @Inject constructor(private val repository : OpenAIApiRepository) : ViewModel() {
}