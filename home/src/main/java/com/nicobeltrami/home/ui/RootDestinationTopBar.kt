package com.nicobeltrami.home.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nicobeltrami.home.Destination
import com.nicobeltrami.home.R

@Composable
fun RootDestinationTopBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    openDrawer: () -> Unit,
    showSnackBar: (message: String) -> Unit
) {
    TopAppBar(modifier = modifier, title = {
        Text(text = Destination.Home.title)
    }, navigationIcon = {
        IconButton(onClick = { openDrawer() }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = stringResource(id = R.string.cd_open_menu)
            )
        }
    },
        actions = {
            if (currentDestination != Destination.Feed) {
                val snackBarMessage = stringResource(
                    id = R.string.not_available_yet
                )
                IconButton(onClick = {
                    showSnackBar(snackBarMessage)
                }) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = stringResource(id = R.string.cd_more_information)
                    )
                }
            }
        }
    )
}