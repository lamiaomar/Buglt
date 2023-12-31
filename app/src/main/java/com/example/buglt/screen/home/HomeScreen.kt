package com.example.buglt.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.buglt.dto.Ticket
import com.example.buglt.TicketsViewModel
import com.example.buglt.screen.loader.IndeterminateCircularIndicator

/**
 * Composable function representing the hom screen.
 */
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: TicketsViewModel
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val ticketList = remember { mutableStateListOf<Ticket>() }
    var isDataFetched by remember { mutableStateOf(false) }

    // Fetch data if not already fetched
    if (!isDataFetched) {
        viewModel.getTickets()
            .apply {
                viewModel.ticketsList.observe(lifecycleOwner) {
                    if (it.isNotEmpty()) {
                        // Clear the existing ticket list and populate it with the fetched data
                        ticketList.clear()
                        isDataFetched = true
                        it.forEach {
                            ticketList.add(
                                Ticket(
                                    title = it.title,
                                    description = it.description,
                                    platformLabel = it.platformLabel,
                                    imageURL = it.imageURL
                                )
                            )
                        }
                    }
                }
            }
    }

    // Display the toolbar at the top of the screen
    HomeToolBar(navController)

    // Display either the ticket list or a loading based on data availability
    if (ticketList.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp, start = 16.dp, end = 16.dp)
        ) {
            // Display the list of tickets using LazyColumn
            LazyColumn {
                items(ticketList) { ticket ->
                    TicketWidget(ticket)
                }
            }
        }
    } else {
        // Display a loading indicator when data is being fetched
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            IndeterminateCircularIndicator()
        }
    }
}

