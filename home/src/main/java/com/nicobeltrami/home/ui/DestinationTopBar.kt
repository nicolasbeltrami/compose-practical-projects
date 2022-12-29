package com.nicobeltrami.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nicobeltrami.home.model.Destination

@Composable
fun DestinationTopBar(
    modifier: Modifier = Modifier,
    destination: Destination,
    onNavigateUp: () -> Unit,
    onOpenDrawer: () -> Unit,
    showSnackBar: (message: String) -> Unit
) {
    if (destination.isRootDestination) {
        RootDestinationTopBar(
            modifier = modifier,
            currentDestination = destination,
            openDrawer = onOpenDrawer,
            showSnackBar = showSnackBar
        )
    } else {
        ChildDestinationTopBar(
            modifier = modifier,
            onNavigateUp = onNavigateUp,
            title = destination.title
        )
    }
}