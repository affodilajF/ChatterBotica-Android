package com.example.chatterboticaapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.chatterboticaapp.R

@Composable
fun MiniProfile(){
    Image(
        painter = painterResource(id = R.drawable.temprofilepic),
        contentDescription = "profile pic",
        modifier = Modifier
            .size(18.dp)
            .clip(CircleShape)
    )
}

@Composable
fun MediumProfile(){
    Image(
        painter = painterResource(id = R.drawable.temprofilepic),
        contentDescription = "profile pic",
        modifier = Modifier
            .size(56.dp)
            .padding(4.dp)
            .clip(CircleShape)
    )
}