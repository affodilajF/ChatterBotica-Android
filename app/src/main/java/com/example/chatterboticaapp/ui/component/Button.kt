package com.example.chatterboticaapp.ui.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatterboticaapp.data.model.VoiceToTextParserState
import com.example.chatterboticaapp.ui.theme.Green01
import com.example.chatterboticaapp.ui.theme.Grey01
import com.example.chatterboticaapp.ui.theme.PrimaryGreen
import com.example.chatterboticaapp.utils.VoiceToTextParser

@Composable
fun TextButton(){
    Surface(
        color = Green01,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .size(width = 130.dp, height = 40.dp)
            .clickable(
                onClick = {
                }
            ),

        ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(
                text = "Lets Talk!",
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                textAlign = TextAlign.End
            )
        }
    }
}


@Composable
fun IconTextButton(icon: Int,
           iconColor: Color,
           txtColor: Color,
           btnColor: Color,
           btnTxt: String,
           onClickFunction: () -> Unit)
{
    Surface(
        color = btnColor,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .size(width = 135.dp, height = 44.dp)
            .clickable(
                onClick = {
                    onClickFunction()
                }
            ),

        ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Text(

                text = btnTxt,
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold, color = txtColor),
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                painter = painterResource(icon),
                contentDescription = "Icon",
                tint = iconColor,
            )
        }
    }
}