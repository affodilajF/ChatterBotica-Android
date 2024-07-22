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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DocsViewModel @Inject constructor() : ViewModel() {

    // STATEFLOW
    // State to hold the list of PDF files
    private val _pdfFiles = MutableStateFlow<List<File>>(emptyList())
    val pdfFiles: StateFlow<List<File>> get() = _pdfFiles

    // State for holding the count of files
    private val _fileSize = MutableStateFlow(_pdfFiles.value.size)
    val fileSize: StateFlow<Int> get() = _fileSize

    // Method to load PDF files asynchronously
    fun loadPdfFiles(ctx : Context) {
        viewModelScope.launch {
            val files = getPdfFiles(ctx) // Implement this function as needed
            _pdfFiles.value = files
        }
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