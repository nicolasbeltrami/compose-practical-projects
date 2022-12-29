package com.nicobeltrami.home.ui

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.nicobeltrami.home.model.Destination
import com.nicobeltrami.home.model.NavigationBarItem.Companion.buildNavigationItems
import com.nicobeltrami.home.R

@Composable
fun RailNavigationBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit,
    onCreateItem: () -> Unit
) {
    NavigationRail(
        modifier = modifier,
        header = {
            FloatingActionButton(onClick = { onCreateItem() }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.cd_create_item)
                )
            }
        }
    ) {
        buildNavigationItems(
            currentDestination = currentDestination,
            onNavigate = onNavigate
        ).forEach {
            NavigationRailItem(
                selected = it.selected,
                onClick = it.onClick,
                icon = it.icon,
                label = it.label
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RailNavigationBarPreview() {
    RailNavigationBar(currentDestination = Destination.Calendar, onNavigate = {}, onCreateItem = {})
}