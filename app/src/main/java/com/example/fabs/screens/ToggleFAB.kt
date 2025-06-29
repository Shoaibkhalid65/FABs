package com.example.fabs.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FormatListNumberedRtl
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleFloatingActionButton
import androidx.compose.material3.ToggleFloatingActionButtonDefaults
import androidx.compose.material3.ToggleFloatingActionButtonDefaults.animateIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fabs.R
import java.nio.file.WatchEvent

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ToggleFab(){
    var isChecked by remember { mutableStateOf(false) }
    ToggleFloatingActionButton(
        checked = isChecked,
        onCheckedChange = {
            isChecked=it
        },
        modifier = Modifier.padding(bottom = 15.dp, end = 15.dp),
        containerColor = ToggleFloatingActionButtonDefaults.containerColor(Color.Cyan,Color.Green),
        contentAlignment = Alignment.TopEnd,
        containerSize = ToggleFloatingActionButtonDefaults.containerSize(60.dp,70.dp),
        containerCornerRadius = ToggleFloatingActionButtonDefaults.containerCornerRadius(12.dp,12.dp)
    ) {

        Icon(
            imageVector = if(isChecked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "add icon",
            modifier = Modifier.animateIcon({checkedProgress})
        )
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ToggleMenu() {
    var isExpended by remember { mutableStateOf(false) }
    FloatingActionButtonMenu(
        expanded = isExpended,
        button = {
            FloatingActionButton(
                onClick = { isExpended = !isExpended },
                shape = RoundedCornerShape(12.dp),
                containerColor = colorResource(R.color.green),
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 12.dp,
                    pressedElevation = 6.dp
                )
            ) {
                Icon(
                    imageVector = if (!isExpended) Icons.Default.Add else Icons.Default.Cancel,
                    contentDescription = "add icon"
                )
            }
        },
    ) {
        if (isExpended) {
            Column {
             ToggleMenuItem("Home", Icons.Default.Home)
             ToggleMenuItem("List", Icons.Default.FormatListNumberedRtl)
             ToggleMenuItem("Notify", Icons.Default.Notifications)

            }
        }
    }
}
@Composable
fun ToggleMenuItem(text: String,imageVector: ImageVector){
    Row {
        Text(
            text = text,
            modifier = Modifier.padding(top = 9.dp).height(28.dp).width(70.dp).background(color = Color.DarkGray, shape = RoundedCornerShape(6.dp)).padding(top = 2.dp),
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Spacer(Modifier.width(10.dp))
        SmallFloatingActionButton(
            onClick = {},
            shape = RoundedCornerShape(16.dp),
            containerColor = colorResource(R.color.green)
        ) {
            Icon(
                imageVector,"home icon",
                tint = Color.White
            )
        }
    }
    Spacer(Modifier.height(10.dp))
}
