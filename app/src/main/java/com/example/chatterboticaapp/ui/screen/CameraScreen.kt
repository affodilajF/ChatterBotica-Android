package com.example.chatterboticaapp.ui.screen

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.chatterboticaapp.ui.navigation.Routes
import com.example.chatterboticaapp.ui.viewmodel.TextExtractionViewModel
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.RESULT_FORMAT_JPEG
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions.SCANNER_MODE_FULL
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult


@Composable
fun CameraScreen(navController: NavController, textExtractViewModel: TextExtractionViewModel) {

    val activity = LocalContext.current as Activity
    val context = LocalContext.current

    val permissionGranted = remember { mutableStateOf(false) }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        permissionGranted.value = isGranted
    }


    val scannerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val res = GmsDocumentScanningResult.fromActivityResultIntent(result.data)
            val img: Uri? = res?.pages?.firstOrNull()?.imageUri

            textExtractViewModel.setImgUri(img)
            textExtractViewModel.extractText(context)
            navController.navigate(Routes.TEXT_EXTRACTION_SCREEN)
        } else {
            navController.popBackStack(navController.graph.startDestinationId, false)
        }
    }

    fun startDocumentScanner() {
        val options = GmsDocumentScannerOptions.Builder()
            .setScannerMode(SCANNER_MODE_FULL)
            .setGalleryImportAllowed(true)
            .setResultFormats(RESULT_FORMAT_JPEG)
            .setPageLimit(1)
            .build()

        val scanner = GmsDocumentScanning.getClient(options)

        try {
            scanner.getStartScanIntent(activity)
                .addOnSuccessListener { intentSender ->
                    scannerLauncher.launch(
                        IntentSenderRequest.Builder(intentSender).build()
                    )
                }
                .addOnFailureListener { exception ->
                    exception.message?.let { message ->
                        Log.e("Scan Failed", message)
                    }
                }
        } catch (e: Exception) {
            Log.e("Scan Error", "An error occurred while starting the scan", e)
        }
    }

    // Start document scanner or start launcher
    LaunchedEffect(permissionGranted.value) {
        if (permissionGranted.value) {
            startDocumentScanner()
        } else {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }
}

