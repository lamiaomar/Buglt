package com.example.buglt.screen.home

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.buglt.dto.Ticket
import com.example.buglt.TicketsViewModel
import com.example.buglt.navigation.Screens
import com.example.buglt.screen.loader.IndeterminateCircularIndicator


@Composable
fun HomeScreen(
    navController: NavHostController,
    context: Context,
    viewModel: TicketsViewModel
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val ticketList = remember { mutableStateListOf<Ticket>() }
    var isDataFetched by remember { mutableStateOf(false) }

    if (!isDataFetched) {
        viewModel.getTickets()
            .apply {
                viewModel.ticketsList.observe(lifecycleOwner) {
                    if (it.isNotEmpty()) {
                        ticketList.clear()
                        isDataFetched = true
                        it.forEach {
                            ticketList.add(
                                Ticket(
                                    title = it.title,
                                    description = it.description,
                                    platformLabel = it.platformLabel
                                )
                            )
                        }
                    }
                }
            }
    }


    if (ticketList.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Ticket List
            LazyColumn {
                items(ticketList) { ticket ->
                    TicketWidget(ticket)
                }
            }

            // Circle Button with "+" icon
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(CircleShape)
                    .height(50.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        navController.navigate(Screens.OpenTicket.route)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    } else {
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

