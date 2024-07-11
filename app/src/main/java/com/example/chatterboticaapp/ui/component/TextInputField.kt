package com.example.chatterboticaapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatterboticaapp.ui.theme.GreyPurple01


@Composable
fun TextInputField(text: String, onTextChange: (String) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = GreyPurple01, shape = RoundedCornerShape(8.dp))
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 13.dp)
            ,
            value = text,
            onValueChange = onTextChange,
            textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
            decorationBox = { innerTextField ->
                innerTextField()
            },
        )
    }
}