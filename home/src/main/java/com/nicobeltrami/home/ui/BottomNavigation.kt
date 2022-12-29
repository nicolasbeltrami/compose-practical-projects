package com.nicobeltrami.home.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nicobeltrami.home.model.Destination
import com.nicobeltrami.home.model.NavigationBarItem.Companion.buildNavigationItems

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit
) {
    BottomNavigation(modifier = modifier) {
        buildNavigationItems(
            currentDestination,
            onNavigate = onNavigate
        ).forEach { destination ->
            BottomNavigationItem(
                selected = destination.selected,
                icon = destination.icon,
                onClick = destination.onClick,
                label = destination.label
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    BottomNavigationBar(currentDestination = Destination.Home, onNavigate = {})
}