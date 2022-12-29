package com.nicobeltrami.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DrawerValue
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nicobeltrami.home.model.Destination
import com.nicobeltrami.home.R
import kotlinx.coroutines.launch

@Composable
fun Home(
    modifier: Modifier = Modifier,
    orientation: Int
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scaffoldState = rememberScaffoldState(drawerState)
    val navController = rememberNavController()
    val navBackStackEntry =
        navController.currentBackStackEntryAsState()

    val currentDestination by remember(navBackStackEntry) {
        derivedStateOf {
            navBackStackEntry.value?.destination?.route?.let {
                Destination.fromString(it)
            } ?: run {
                Destination.Home
            }
        }
    }

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = {
            DestinationTopBar(
                destination = currentDestination,
                onNavigateUp = { navController.popBackStack() },
                onOpenDrawer = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                },
                showSnackBar = {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState
                            .showSnackbar(it)
                    }
                }
            )
        },
        drawerContent = {
            DrawerContent(
                modifier = Modifier.fillMaxWidth(),
                onNavigationSelected = { destination ->
                    navController.navigate(destination.path)
                    coroutineScope.launch {
                        drawerState.close()
                    }
                },
                onLogout = {}
            )
        },
        floatingActionButton = {
            if (orientation != Configuration.ORIENTATION_LANDSCAPE &&
                currentDestination == Destination.Feed
            ) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(
                            Destination.Creation.path
                        )
                    }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.cd_create_item)
                    )
                }
            }
        },
        bottomBar = {
            if (orientation == Configuration.ORIENTATION_PORTRAIT &&
                currentDestination.isRootDestination
            ) {
                BottomNavigationBar(
                    currentDestination = currentDestination,
                    onNavigate = {
                        navController.navigate(it.path) {
                            popUpTo(
                                navController.graph.findStartDestination().id
                            ) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) {
        Body(
            modifier = Modifier,
            navController = navController,
            destination = currentDestination,
            orientation = orientation,
            onCreateItem = { navController.navigate(Destination.Add.path) },
            onNavigate = {
                navController.navigate(it.path) {
                    popUpTo(Destination.Home.path) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }

            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home(modifier = Modifier.fillMaxSize(), orientation = Configuration.ORIENTATION_LANDSCAPE)
}