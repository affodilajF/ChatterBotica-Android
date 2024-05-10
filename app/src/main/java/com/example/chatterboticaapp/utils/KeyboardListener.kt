package com.example.chatterboticaapp.utils

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun KeyboardListener(onKeyboardVisibilityChanged: (Boolean) -> Unit) {
    val context = LocalContext.current

    AndroidView(factory = { context ->
        View(context).apply {
            // Menambahkan listener ketika fokus berubah
            setOnFocusChangeListener { _, hasFocus ->
                onKeyboardVisibilityChanged(hasFocus)
            }
        }
    })
}