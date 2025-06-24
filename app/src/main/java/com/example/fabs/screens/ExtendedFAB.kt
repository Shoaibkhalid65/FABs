package com.example.fabs.screens

import android.widget.Toast
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContactPhone
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import java.nio.file.WatchEvent

@Composable
fun ExtendedFAB(){
    val context= LocalContext.current
    val viewConfiguration= LocalViewConfiguration.current
    val interactionSource= remember{ MutableInteractionSource() }
    LaunchedEffect(interactionSource) {
        var isLongClick=false
        interactionSource.interactions.collectLatest {interaction->
            when(interaction){
                is PressInteraction.Press->{
                    isLongClick=false
                    delay(viewConfiguration.longPressTimeoutMillis)
                    isLongClick=true
                    Toast.makeText(context,"Long clicked!", Toast.LENGTH_SHORT).show()
                }
                is PressInteraction.Release->{
                    if(isLongClick.not()){
                        Toast.makeText(context,"Clicked!", Toast.LENGTH_SHORT).show()
                    }
                }
                is PressInteraction.Cancel->{
                    isLongClick=false
                }
            }
        }
    }
    ExtendedFloatingActionButton(
        onClick = {},
        shape = RoundedCornerShape(12.dp),
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary,
        interactionSource = interactionSource
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Add Contact"
            )
            Icon(
                imageVector = Icons.Default.ContactPhone,
                contentDescription = "contact phone",
                modifier = Modifier.padding(start = 10.dp)
            )

        }
    }
}