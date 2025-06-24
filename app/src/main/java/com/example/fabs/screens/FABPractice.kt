package com.example.fabs.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButtonMenu
import androidx.compose.material3.FloatingActionButtonMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleFloatingActionButton
import androidx.compose.material3.ToggleFloatingActionButtonDefaults.animateIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalViewConfiguration
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FabItemsMenu(){
    val items=listOf(
        Triple(Icons.Default.Edit ,"Edit",Color(0xFF4CAF50)),
        Triple(Icons.Default.FileOpen , "Open File",Color(0xFF795548)),
        Triple(Icons.Default.AddLocation , "Add Location",Color(0xFFF44336)),
        Triple(Icons.Default.Favorite , "Add to Fav",Color(0xFFFF9800)),
        Triple(Icons.Default.Settings , "Settings",Color(0xFF2196F3))
    )
    var isExpanded by remember { mutableStateOf(false) }
    BackHandler (isExpanded){ isExpanded=false }
    FloatingActionButtonMenu(
        expanded = isExpanded,
        button = {
            ToggleFloatingActionButton(
                checked = isExpanded,
                onCheckedChange = {
                    isExpanded=!isExpanded
                },
            ) {
                Icon(
                    imageVector = if(isExpanded) Icons.Default.Close else Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.animateIcon({checkedProgress})
                )
            }
        }
    ) {
        items.forEach {item->
            FloatingActionButtonMenuItem(
                onClick = {isExpanded=false},
                text = {Text(text = item.second)},
                icon = {Icon(imageVector = item.first, contentDescription = null)},
                containerColor = item.third,
                contentColor = Color.White
            )
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun LongClickableFab(){
    val context= LocalContext.current
    val viewConfiguration= LocalViewConfiguration.current
    val interactionSource = remember { MutableInteractionSource() }
    LaunchedEffect(interactionSource) {
        var isLongClicked=false
        interactionSource.interactions.collectLatest { interaction->
            when(interaction){
                is PressInteraction.Press->{
                    isLongClicked=false
                    delay(viewConfiguration.longPressTimeoutMillis)
                    isLongClicked=true
                    Toast.makeText(context,"Fab long clicked!", Toast.LENGTH_SHORT).show()
                }
                is PressInteraction.Release->{
                    if(!isLongClicked){
                        Toast.makeText(context,"Fab clicked!", Toast.LENGTH_SHORT).show()
                    }
                }
                is PressInteraction.Cancel ->{
                    isLongClicked=false
                }
            }
        }
    }
    MediumFloatingActionButton(
        onClick = {},
        interactionSource=interactionSource
    ) {
        Icon(
            imageVector = Icons.Default.Edit, contentDescription = null
        )
    }
}