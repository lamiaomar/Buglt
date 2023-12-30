package com.example.buglt.navigation

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.buglt.images.UploadScreenShotManager
import com.example.buglt.screen.HomeScreen
import com.example.buglt.screen.openticket.OpenTicketScreen
import com.example.buglt.screen.SplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetUpNavGraph(
    navController: NavHostController,
    context: Context,
    uploadScreenShotManager: UploadScreenShotManager
) {

    NavHost(
        navController = navController,
        startDestination = Screens.OpenTicket.route
    ) {

        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screens.OpenTicket.route) {
            OpenTicketScreen(
                navController = navController,
                context = context,
                uploadScreenShotManager = uploadScreenShotManager
            )
        }
        composable(route = Screens.Home.route) {
            HomeScreen(navController = navController)
        }
    }
}