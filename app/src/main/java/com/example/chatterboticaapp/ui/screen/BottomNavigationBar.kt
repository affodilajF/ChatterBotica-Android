package com.example.chatterboticaapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.TabRowDefaults.contentColor
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chatterboticaapp.R
import com.example.chatterboticaapp.ui.theme.Grey02
import com.example.chatterboticaapp.ui.theme.GreyPurple01


@Preview
@Composable
fun BottomNavigationBarPreview(){
    BottomNavigationBar()
}

@Composable
fun BottomNavigationBar(){

    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Home", "Chat", "Mic", "Docs")
    val icons = listOf(R.drawable.home, R.drawable.chat, R.drawable.micon, R.drawable.doc)

    Surface(
        shape = RoundedCornerShape(25.dp),
        color = GreyPurple01
    ) {
        NavigationBar(containerColor = Color.Transparent) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = { Icon( painter = painterResource(icons[index]),  tint = if (selectedItem == index)  Color.White else  Grey02, contentDescription = item, modifier = Modifier.size(36.dp)) },
                    selected = selectedItem == index,
                    onClick = { selectedItem = index},
                    colors = androidx.compose.material3.NavigationBarItemDefaults
                        .colors(
                            indicatorColor = GreyPurple01
                        )
                    )
            }
        }
    }
}



