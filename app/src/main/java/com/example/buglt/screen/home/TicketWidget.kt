package com.example.buglt.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buglt.dto.Ticket

@Composable
fun TicketWidget(ticket: Ticket) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(text = ticket.title, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = ticket.description, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(30.dp)
                .background(getColor(ticket.platformLabel))
                .clip(CircleShape),

            ) {
            Text(
                text = ticket.platformLabel,
                color = Color.White,
                fontSize = 13.sp,
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }
    }
}

fun getColor(platform: String): Color {
    return when(platform) {
        "Android" -> {
            Color.Cyan
        }
        "iOS" -> {
            Color.Blue
        }

        "PWA" -> {
            Color.Black
        }
        else -> {
            Color.Yellow
        }
    }
}
