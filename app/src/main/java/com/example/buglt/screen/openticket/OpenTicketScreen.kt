package com.example.buglt.screen.openticket

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.buglt.R
import com.example.buglt.TicketsViewModel

/**
 * Composable function representing the Open Ticket screen.
 */
@ExperimentalAnimationApi
@Composable
fun OpenTicketScreen(
    navController: NavHostController?,
    context: Context,
    viewModel: TicketsViewModel
) {

    // Display the toolbar at the top of the screen
    OpenTicketToolbar(navController)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp)
            .background(Color.White)
    ) {

        // Display the title of the open ticket form
        Text(
            text = context.getString(R.string.open_ticket_form_title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp, start = 10.dp, end = 10.dp)
        )

        // Display the open ticket form
        TicketForm(context = context, viewModel = viewModel)

        // Display the "Create Ticket" button
        Button(
            onClick = {
                // Check if required data is filled before creating a ticket
                if (!viewModel.createTicketBody?.title.isNullOrBlank() &&
                    !viewModel.createTicketBody?.description.isNullOrBlank() &&
                    !viewModel.createTicketBody?.platform.isNullOrBlank()
                ) {
                    viewModel.createTicket()
                } else {
                    // Show a message if required data is not filled
                    showMessage(
                        context = context,
                        message = context.getString(R.string.fill_all_required_data)
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 10.dp, end = 10.dp)
                .height(50.dp)
        ) {
            Text(context.getString(R.string.create_ticket_button))
        }
    }
}

/**
 * Function to display a toast message. When mandatory fields are empty.
 */
fun showMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}