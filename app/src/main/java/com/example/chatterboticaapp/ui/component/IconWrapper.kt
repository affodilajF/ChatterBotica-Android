package com.example.chatterboticaapp.ui.component

import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.chatterboticaapp.R
import com.example.chatterboticaapp.ui.theme.Grey01
import com.example.chatterboticaapp.ui.theme.GreyPurple01
import kotlinx.coroutines.launch

@Composable
fun RoundedIconWrapperMini(drawableIcon: Int, colorWrapper: Color){
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(color = colorWrapper, shape = RoundedCornerShape(percent = 50)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(drawableIcon),
            contentDescription = "Icon",
            tint = Grey01,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun RoundedIconWrapperMedium(drawableIcon: Int, colorWrapper: Color, colorIcon : Color, onClickFunction : suspend () -> Unit){

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .size(46.dp)
            .clickable {
                coroutineScope.launch {
                    onClickFunction()
                }
            }
            .background(color = colorWrapper, shape = RoundedCornerShape(percent = 50)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(drawableIcon),
            contentDescription = "Icon",
            tint = colorIcon,
            modifier = Modifier.size(24.dp)
        )
    }
}

