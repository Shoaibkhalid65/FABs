package com.example.fabs.screens

import android.widget.Toast
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeExtendedFloatingActionButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FAB(){
    val context= LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed =interactionSource.collectIsPressedAsState()
    LargeFloatingActionButton(
        onClick = {
            Toast.makeText(context,"FAB clicked!", Toast.LENGTH_SHORT).show()
        },
        interactionSource = interactionSource,
        modifier = Modifier.padding(bottom = 30.dp, end = 30.dp).combinedClickable(
            enabled = true,
            onLongClick = {
                Toast.makeText(context,"FAB long clicked!", Toast.LENGTH_SHORT).show()
            },
            onDoubleClick = {
                Toast.makeText(context,"FAB double clicked!", Toast.LENGTH_SHORT).show()
            },
            onClick = {
                Toast.makeText(context,"FAB clicked! in combined clickable", Toast.LENGTH_SHORT).show()
            }
        ),
        shape = AbsoluteCutCornerShape(16.dp),
        containerColor = if(isPressed.value) Color.Blue else Color.Gray,
        contentColor = Color.Blue,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 8.dp,
            pressedElevation = 6.dp,
            focusedElevation = 12.dp,
            hoveredElevation = 24.dp
        ),
    ) {
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "edit icon"
        )
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun LongClickableFAB(){
    val context=LocalContext.current
    val viewConfiguration= LocalViewConfiguration.current
    val interactionSource= remember { MutableInteractionSource() }
    LaunchedEffect(interactionSource) {
        var isLongClick=false
        interactionSource.interactions.collectLatest {interaction->
            when(interaction){
                is PressInteraction.Press ->{
                    isLongClick=false
                    delay(viewConfiguration.longPressTimeoutMillis)
                    isLongClick=true
                    Toast.makeText(context,"Long clicked!", Toast.LENGTH_SHORT).show()
                }
                is PressInteraction.Release ->{
                    if(isLongClick.not()){
                        Toast.makeText(context,"clicked!", Toast.LENGTH_SHORT).show()
                    }
                }
                is PressInteraction.Cancel ->{
                    isLongClick=false
                }
            }
        }
    }
    ExtendedFloatingActionButton (
        text = {
            Text(
                text = "Add Note"
            )
        },
        icon = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "add icon"
            )
        },
        onClick = {},
        expanded = true,
        interactionSource = interactionSource,
        modifier = Modifier.padding(end = 15.dp, bottom = 15.dp)
    )

}