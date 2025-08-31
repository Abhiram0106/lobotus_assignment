package com.github.abhiram0106.lobotus_assignment.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.abhiram0106.lobotus_assignment.R
import com.github.abhiram0106.lobotus_assignment.core.navigation.AppNavHost
import com.github.abhiram0106.lobotus_assignment.core.ui.theme.AppTheme

@Composable
fun App(
    appState: LobotusAssignmentAppState = rememberLobotusAssignmentAppState(),
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    var expandFAB by remember {
        mutableStateOf(false)
    }

    AppTheme {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                ) {
                    appState.topLevelDestinations.forEach { topLevelDestination ->
                        val isSelected =
                            appState.isBottomNavItemSelected(topLevelDestination.baseRoute)
                        NavigationBarItem(
                            selected = isSelected,
                            alwaysShowLabel = false,
                            label = if (isSelected) {
                                {
                                    Text(
                                        text = topLevelDestination.displayText.asString(),
                                        style = MaterialTheme.typography.labelSmall,
                                    )
                                }
                            } else {
                                null
                            },
                            onClick = {
                                appState.navigateToTopLevelDestination(topLevelDestination)
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(
                                        if (isSelected) {
                                            topLevelDestination.icon
                                        } else {
                                            topLevelDestination.unselectedIcon
                                        }
                                    ),
                                    contentDescription = stringResource(
                                        R.string.navigate_to_x,
                                        topLevelDestination.displayText
                                    ),
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                                indicatorColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
            },
            floatingActionButton = {
                if (appState.showFAB()) {
                    ExtendedFloatingActionButton(
                        onClick = {},
                        expanded = expandFAB,
                        text = {
                            Text(
                                text = stringResource(R.string.company),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_plus),
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        },
                        shape = CircleShape
                    )
                }
            },
            contentWindowInsets = WindowInsets.safeDrawing
        ) { paddingValues ->
            Box(
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues)
            ) {
                AppNavHost(
                    navHostController = appState.navController,
                    onShowSnackBar = { message, actionLabel ->
                        snackBarHostState.showSnackbar(
                            message = message.asStringNonComposable(context),
                            actionLabel = actionLabel?.asStringNonComposable(context),
                        ) == SnackbarResult.ActionPerformed
                    },
                    onScrollChange = { expandFAB = it }
                )
            }
        }
    }
}