package com.nicobeltrami.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
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
    object Settings :
        Destination("Settings", "settings", Icons.Default.Settings, isRootDestination = false)

    object Upgrade :
        Destination("Upgrade", "upgrade", Icons.Default.Star, isRootDestination = false)
    object Creation : Destination("Creation", "creation", isRootDestination = false)
    object Add : Destination("Add", "add", icon = Icons.Default.Add, isRootDestination = false)

    companion object {
        fun fromString(route: String): Destination {
            return when (route) {
                Feed.path -> Feed
                Calendar.path -> Calendar
                Contacts.path -> Contacts
                Upgrade.path -> Upgrade
                Settings.path -> Settings
                Add.path -> Add
                Creation.path -> Creation
                else -> Home
            }
        }
    }
}
