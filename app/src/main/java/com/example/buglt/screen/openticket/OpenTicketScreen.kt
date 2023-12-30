package com.example.buglt.screen.openticket

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.buglt.R
import com.example.buglt.TicketsViewModel
import com.example.buglt.navigation.Screens


@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalAnimationApi
@Composable
fun OpenTicketScreen(
    navController: NavHostController?,
    context: Context,
    viewModel: TicketsViewModel
) {

    CenterAlignedTopAppBar(
        title = {
            Text(context.getString(R.string.create_ticket_screen_title))
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = Modifier
            .fillMaxWidth(),
        navigationIcon = {
            IconButton(
                onClick = {
                    // TODO route to home screen
                    navController?.navigate(Screens.Splash.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Exist create ticket",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp, start = 10.dp, end = 10.dp)
    ) {

        Text(
            text = context.getString(R.string.open_ticket_form_title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TicketForm(context = context, viewModel = viewModel)

        Button(
            onClick = {
                if (!viewModel.createTicketBody?.title.isNullOrBlank() &&
                    !viewModel.createTicketBody?.description.isNullOrBlank() &&
                    !viewModel.createTicketBody?.platform.isNullOrBlank()
                ) {
                    viewModel.createTicket()
                } else {
                    showMessage(context = context, message = context.getString(R.string.fill_all_required_data))
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 8.dp, end = 8.dp)
                .height(50.dp)
        ) {
            Text(context.getString(R.string.create_ticket_button))
        }
    }
}
fun showMessage(context: Context, message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}