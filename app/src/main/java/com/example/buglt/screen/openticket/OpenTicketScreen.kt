package com.example.buglt.screen.openticket

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.buglt.R
import com.example.buglt.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenTicketScreen(navController: NavHostController?, context: Context) {

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
                onClick = { navController?.navigate(Screens.Splash.route) }
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

        TicketForm(context = context)

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 8.dp, end = 8.dp)
                .height(50.dp)
        ) {
            Text(context.getString(R.string.create_ticket_button))
        }
    }
}