package com.example.chatterboticaapp.ui.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chatterboticaapp.R
import com.example.chatterboticaapp.ui.theme.Black01
import com.example.chatterboticaapp.ui.theme.Grey02
import com.example.chatterboticaapp.ui.theme.Grey03
import com.example.chatterboticaapp.ui.theme.Grey04
import com.example.chatterboticaapp.ui.theme.GreyPurple01
import com.example.chatterboticaapp.ui.theme.GreyPurple02
import com.example.chatterboticaapp.ui.theme.GreyPurple03
import com.example.chatterboticaapp.ui.viewmodel.DocsViewModel
import com.example.chatterboticaapp.utils.PDFUtils.getPdfFiles
import com.example.chatterboticaapp.utils.PDFUtils.openPdfFile
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import java.io.File

@Composable
fun DocsScreen() {
    val context = LocalContext.current
    val docsViewModel: DocsViewModel = hiltViewModel()

    // Use mutableStateListOf to hold and observe the list of PDF files
    val pdfFileSize by docsViewModel.fileSize.collectAsState()
    // if you r using flow
    val pdfFiles by docsViewModel.pdfFiles.collectAsState()
    // if you r using live data
//    val pdfFiles by docsViewModel.pdfFiles.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        docsViewModel.loadPdfFiles(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Black01)
            .padding(horizontal = 18.dp, vertical = 22.dp)
        ,

    ) {
        Text(
            text = "Your PDF Files",
            style = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp) // Optional: Makes the Text fill the width of the parent
        )

        if(pdfFiles.isNotEmpty()){
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(pdfFiles.reversed()) { file ->
                    PdfFileItem(file, context)
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

                Text(
                    text = "No data found",
                    style = TextStyle(
                        color = Grey03,
                        fontSize = 24.sp,
                    ),
                    modifier = Modifier
                        .padding(vertical = 20.dp) // Optional: Makes the Text fill the width of the parent
                )
            }
        }
    }
}

// Composable function to display each PDF file item
@Composable
fun PdfFileItem(file: File, context: Context) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                openPdfFile(context, file)
            },
        color = Grey04,
        shape = RoundedCornerShape(8.dp), // Rounded corners
        shadowElevation = 4.dp // Shadow for elevation effect
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp) // Padding inside the Surface
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_picture_as_pdf_24), // Ensure you have this resource
                contentDescription = "PDF Icon",
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp),
                tint = Color.White
            )

            Text(
                text = file.name,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 13.sp,
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 5.dp, bottom = 5.dp)
            )
        }
    }
}



