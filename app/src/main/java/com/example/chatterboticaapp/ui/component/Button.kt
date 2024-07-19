package com.example.chatterboticaapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatterboticaapp.ui.theme.Green01
import kotlinx.coroutines.launch

@Composable
fun TextButton(onClick: () -> Unit){
    Surface(
        color = Green01,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .size(width = 130.dp, height = 40.dp)
            .clickable(
                onClick = {
                    onClick()
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
           onClickFunction: suspend () -> Unit)
{

    val coroutineScope = rememberCoroutineScope()

    Surface(
        color = btnColor,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .size(width = 135.dp, height = 44.dp)
            .clickable {
                coroutineScope.launch {
                    onClickFunction()
                }
            }

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