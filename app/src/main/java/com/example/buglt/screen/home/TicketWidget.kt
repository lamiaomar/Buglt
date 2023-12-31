package com.example.buglt.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buglt.dto.Ticket
import com.example.buglt.R
import com.example.buglt.ui.theme.Purple80

/**
 * Composable function representing a ticket widget for displaying ticket information.
 */
@Composable
fun TicketWidget(ticket: Ticket) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.background)
            .border(
                width = 2.dp,
                color = Purple80,
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp)
            )
            .padding(16.dp)
    ) {
        // Display the title of the ticket
        Text(text = ticket.title, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        // Display the description of the ticket
        Text(text = ticket.description, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        // Display a colored box representing the platform label
        Box(
            modifier = Modifier
                .width(60.dp)
                .height(30.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(getColor(ticket.platformLabel)),
        ) {
            // Display the platform label text in the colored box
            Text(
                text = ticket.platformLabel,
                color = Color.White,
                fontSize = 13.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

/**
 * Function to get the color based on the platform label.
 *
 * @param platform The platform label for which the color is determined.
 * @return Color corresponding to the platform label.
 */
@Composable
fun getColor(platform: String): Color {
    return when(platform) {
        stringResource(id = R.string.button_radio_android) -> {
            MaterialTheme.colorScheme.error
        }
        stringResource(id = R.string.button_radio_ios) -> {
            MaterialTheme.colorScheme.surface
        }

        stringResource(id = R.string.button_radio_pwa) -> {
            MaterialTheme.colorScheme.scrim
        }
        else -> {
            MaterialTheme.colorScheme.background
        }
    }
}
