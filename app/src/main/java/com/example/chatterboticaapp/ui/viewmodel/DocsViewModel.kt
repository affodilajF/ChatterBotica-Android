package com.example.chatterboticaapp.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatterboticaapp.utils.PDFUtils.getPdfFiles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chatterboticaapp.utils.PDFUtils
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DocsViewModel @Inject constructor() : ViewModel() {

    // STATEFLOW
    // State to hold the list of PDF files
    private val _pdfFiles = MutableStateFlow<List<File>>(emptyList())
    val pdfFiles: StateFlow<List<File>> get() = _pdfFiles

    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading


    // Method to load PDF files asynchronously
    fun loadPdfFiles(ctx : Context) {
        viewModelScope.launch {
            try {
                val files = getPdfFiles(ctx) // Implement this function as needed
                _pdfFiles.value = files
            } finally {
                delay(400)
                _isLoading.value = false
            }
        }
    }

    fun deletePdfFile(context: Context, file: File) {
        viewModelScope.launch {
            val deleted = PDFUtils.deletePdfFile(context, file)
            if (deleted) {
                loadPdfFiles(context)
            }
        }
    }

    fun openPdfFile(context: Context, file: File){
        PDFUtils.openPdfFile(context, file)
    }


    // LIVEDATA
//    MutableLiveData to hold and observe the list of PDF files
//    private val _pdfFiles = MutableLiveData<List<File>>(emptyList())
//    val pdfFiles: LiveData<List<File>> get() = _pdfFiles
//
//    fun loadPdfFiles(context: Context) {
//        viewModelScope.launch {
//            // Simulate loading files
//            val files = getPdfFiles(context) // Implement this function as needed
//            _pdfFiles.postValue(files) // Update LiveData
//        }
//    }





}