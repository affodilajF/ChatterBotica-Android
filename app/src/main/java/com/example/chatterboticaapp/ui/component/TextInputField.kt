package com.example.chatterboticaapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatterboticaapp.ui.theme.GreyPurple01


@Composable
fun TextInputField(txtHint: String,txtColor: Color, bgColor: Color, text: String, onTextChange: (String) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = bgColor, shape = RoundedCornerShape(8.dp))
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 13.dp)
            ,
            value = text,
            onValueChange = onTextChange,
            textStyle = TextStyle(color = txtColor, fontSize = 14.sp),
            decorationBox = { innerTextField ->
                // Define a Box to contain both the inner text field and hint text
                androidx.compose.foundation.layout.Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    if (text.isEmpty()) {
                        // Show hint text when text is empty
                        Text(
                            text = txtHint,
                            style = TextStyle(color = Color.Gray)
                        )
                    }
                    // Show the actual text field on top of the hint text
                    innerTextField()
                }
            },
        )
    }
}