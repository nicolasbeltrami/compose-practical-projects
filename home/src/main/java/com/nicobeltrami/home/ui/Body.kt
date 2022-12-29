package com.nicobeltrami.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.nicobeltrami.home.model.Destination
import com.nicobeltrami.home.Navigation

@Composable
fun Body(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    destination: Destination,
    orientation: Int,
    onCreateItem: () -> Unit,
    onNavigate: (destination: Destination) -> Unit
) {
    Row(modifier = modifier) {
        if (destination.isRootDestination &&
            orientation == Configuration.ORIENTATION_LANDSCAPE
        ) {
            RailNavigationBar(
                modifier = modifier,
                currentDestination = destination,
                onCreateItem = onCreateItem,
                onNavigate = onNavigate
            )
        }
        Navigation(
            modifier = modifier.fillMaxSize(),
            navController = navController
        )
    }
}