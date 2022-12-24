package com.nicobeltrami.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination(
    val title: String,
    val path: String,
    val icon: ImageVector? = null,
    val isRootDestination: Boolean = true
) {
    object Home : Destination("Home", "home")
    object Feed : Destination("Feed", "feed", Icons.Default.List)
    object Contacts : Destination("Contacts", "contacts", Icons.Default.Person)
    object Calendar : Destination("Calendar", "calendar", Icons.Default.DateRange)

    companion object {
        fun fromString(route: String): Destination {
            return when (route) {
                Feed.path -> Feed
                Calendar.path -> Calendar
                Contacts.path -> Contacts
                else -> Home
            }
        }
    }
}
