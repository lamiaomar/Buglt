package com.example.buglt.navigation

sealed class Screens(val route : String){
    object Splash : Screens("splash_screen")
    object OpenTicket : Screens("open_ticket_screen")
    object Home : Screens("home_screen")
}