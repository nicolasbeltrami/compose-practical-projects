package com.nicobeltrami.home.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DrawerValue
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nicobeltrami.home.Navigation
import com.nicobeltrami.home.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.nicobeltrami.home.Destination
import kotlinx.coroutines.launch

@Composable
fun Home(
    modifier: Modifier = Modifier
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
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.cd_create_item)
                )
            }
        },
        bottomBar = {
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
    ) {
        Navigation(
            modifier = modifier,
            navController = navController
        )
    }
}