package com.nicobeltrami.home.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nicobeltrami.home.Destination

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit
) {
    BottomNavigation(modifier = modifier) {
        listOf(
            Destination.Feed,
            Destination.Contacts,
            Destination.Calendar,
        ).forEach { destination ->
            BottomNavigationItem(
                selected = currentDestination.path == destination.path,
                icon = {
                    destination.icon?.let { image ->
                        Icon(
                            imageVector = image,
                            contentDescription = destination.path
                        )
                    }
                }, onClick = {
                    onNavigate(destination)
                },
                label = {
                    Text(text = destination.title)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    BottomNavigationBar(currentDestination = Destination.Home, onNavigate = {})
}