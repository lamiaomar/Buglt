package com.example.buglt.navigation

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.buglt.TicketsViewModel
import com.example.buglt.screen.home.HomeScreen
import com.example.buglt.screen.openticket.OpenTicketScreen
import com.example.buglt.screen.splash.SplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi

/**
 * Composable function responsible for setting up the navigation graph using Jetpack Compose.
 *
 * @param navController Navigation controller for handling navigation actions.
 * @param context Application context.
 * @param viewModel ViewModel responsible for managing tickets related operations.
 */
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    context: Context,
    viewModel: TicketsViewModel
) {
    // Define the navigation host with a start destination
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {

        // Define a composable for the SplashScreen
        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navController)
        }

        // Define a composable for the OpenTicketScreen
        composable(route = Screens.OpenTicket.route) {
            OpenTicketScreen(
                navController = navController,
                context = context,
                viewModel = viewModel
            )
        }

        // Define a composable for the HomeScreen
        composable(route = Screens.Home.route) {
            HomeScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}