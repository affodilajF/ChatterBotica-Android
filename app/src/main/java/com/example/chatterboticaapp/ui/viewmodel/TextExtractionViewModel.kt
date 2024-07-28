package com.example.chatterboticaapp.ui.viewmodel

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatterboticaapp.utils.uriToInputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class TextExtractionViewModel @Inject constructor() : ViewModel() {

    private var _imgUri = MutableStateFlow<Uri?>(null)
    fun setImgUri(imgUri : Uri?){
        _imgUri.value = imgUri
    }

    private var _textExtraction = MutableStateFlow<String?>(null)
    val textExtraction : StateFlow<String?> = _textExtraction

    fun extractText(context: Context) {
        val image = uriToInputImage(context, _imgUri.value)
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        if (image != null) {
            recognizer.process(image)
                .addOnSuccessListener { visionText ->
                    val resultText = visionText.text
                    _textExtraction.value = resultText
                    Log.d("TextRecognition", "Text recognized: $resultText")
                }
                .addOnFailureListener { e ->
                    Log.e("TextRecognition", "Text recognition error: ${e.message}", e)
                }
        } else {
            Log.d("ExtractText", "Nothing can be extracted")
        }
    }

    fun copyToClipboard(context: Context) {
        viewModelScope.launch {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Copied Text", _textExtraction.value)
            clipboard.setPrimaryClip(clip)
        }
    }

}