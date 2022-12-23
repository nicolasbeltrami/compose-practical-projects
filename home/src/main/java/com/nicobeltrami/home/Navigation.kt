package com.nicobeltrami.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nicobeltrami.home.ui.ContentArea

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destination.Feed.path
    ) {
        composable(route = Destination.Feed.path) {
            ContentArea(
                destination = Destination.Feed
            )
        }
        composable(route = Destination.Contacts.path) {
            ContentArea(
                destination = Destination.Contacts
            )
        }
        composable(route = Destination.Calendar.path) {
            ContentArea(
                destination = Destination.Calendar
            )
        }
    }
}