package com.example.chatterboticaapp.utils

import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.example.chatterboticaapp.data.model.remote.GeminiAiResponse
import com.itextpdf.io.font.PdfEncodings
import com.itextpdf.io.font.constants.StandardFonts
import com.itextpdf.kernel.font.PdfFont
import com.itextpdf.kernel.font.PdfFontFactory
import java.io.File
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Text
import com.itextpdf.layout.properties.TextAlignment
import java.util.UUID


object PDFUtils {

    fun deletePdfFile(context: Context, file: File): Boolean {
        return try {
            val deleted = file.delete()
            if (deleted) {
                context.contentResolver.delete(
                    MediaStore.Files.getContentUri("external"),
                    "${MediaStore.Files.FileColumns.DATA}=?",
                    arrayOf(file.absolutePath)
                )
            }
            deleted
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun openPdfFile(context: Context, file: File) {
        val fileUri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(fileUri, "application/pdf")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        context.startActivity(intent)
    }

    fun getPdfFiles(context: Context): List<File> {
        val directory = context.getExternalFilesDir(null)
        return directory?.listFiles { _, name -> name.endsWith(".pdf") }?.toList() ?: emptyList()
    }

    fun createPdfFile(content: List<GeminiAiResponse>?, context: Context, pdfFileName: String): File? {
        val filePath = "${context.getExternalFilesDir(null)}/${pdfFileName}.pdf"
        val file = File(filePath)

        val boldFont: PdfFont = PdfFontFactory.createFont()
        val generatedTime : String = TimestampUtils.getToday()

        try {
            PdfWriter(file).use { pdfWriter ->
                PdfDocument(pdfWriter).use { pdfDocument ->
                    Document(pdfDocument).use { document ->

                        val font: PdfFont = PdfFontFactory.createFont()
                        val title = Paragraph("ChatterBotica Application")
                            .setFont(font)
                            .setFontSize(20f)

                        val time = Paragraph("Generated in $generatedTime")
                            .setFont(font)
                            .setFontSize(14f)

                        document.add(title.setTextAlignment(TextAlignment.CENTER))
                        document.add(time.setTextAlignment(TextAlignment.CENTER))

                        document.add(Paragraph("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -"))
                        document.add(Paragraph(" "))
                        document.add(Paragraph(" "))
                        document.add(Paragraph(" "))

                        if (content != null) {
                            for (data in content) {
                                document.add(Paragraph().add(Text(data.request).setFont(boldFont).setBold()))
                                document.add(Paragraph(data.response))
                                document.add(Paragraph(""))
                                document.add(Paragraph(""))
                                document.add(Paragraph(""))
                            }
                        }
                    }
                }
            }
            return file
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}