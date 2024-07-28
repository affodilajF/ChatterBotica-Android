package com.example.chatterboticaapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.google.mlkit.vision.common.InputImage
import java.io.InputStream

fun uriToInputImage(context: Context, uri: Uri?): InputImage? {
    uri ?: return null
    val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
    val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
    return InputImage.fromBitmap(bitmap, 0)
}