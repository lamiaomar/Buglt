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

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    context: Context,
    viewModel: TicketsViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {

        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screens.OpenTicket.route) {
            OpenTicketScreen(
                navController = navController,
                context = context,
                viewModel = viewModel
            )
        }
        composable(route = Screens.Home.route) {
            HomeScreen(
                navController = navController,
                viewModel = viewModel,
                context = context
            )
        }
    }
}