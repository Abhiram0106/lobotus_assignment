package com.github.abhiram0106.lobotus_assignment.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.github.abhiram0106.lobotus_assignment.core.navigation.Graphs
import com.github.abhiram0106.lobotus_assignment.core.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.reflect.KClass

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun rememberLobotusAssignmentAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): LobotusAssignmentAppState {

    return remember(
        coroutineScope,
        navController
    ) {
        LobotusAssignmentAppState(
            coroutineScope = coroutineScope,
            navController = navController
        )
    }
}


class LobotusAssignmentAppState(
    coroutineScope: CoroutineScope,
    val navController: NavHostController,
) {

    private val previousDestination = mutableStateOf<NavDestination?>(null)
    val currentDestination: NavDestination?
        @Composable get() {
            val currentEntry = navController.currentBackStackEntryFlow
                .collectAsState(initial = null)

            // Fallback to previousDestination if currentEntry is null
            return currentEntry.value?.destination.also { destination ->
                if (destination != null) {
                    previousDestination.value = destination
                }
            } ?: previousDestination.value
        }

    val topLevelDestinations: Array<TopLevelDestination> =
        TopLevelDestination.entries.toTypedArray()

    @Composable
    fun isBottomNavItemSelected(route: KClass<*>): Boolean {
        return currentDestination.isRouteInHierarchy(route)
    }

    @Composable
    fun showFAB(): Boolean {
        return currentDestination?.hasTopScreenAs(TopLevelDestination.Companies.route) == true
    }

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        if (navController.currentDestination.hasTopScreenAs(topLevelDestination.route)) {
            return
        }

        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        navController.navigate(
            route = when (topLevelDestination) {
                TopLevelDestination.Home -> {
                    Graphs.Home
                }

                TopLevelDestination.Companies -> {
                    Graphs.Companies
                }

                TopLevelDestination.Settings -> {
                    Graphs.Settings
                }
            },
            navOptions = topLevelNavOptions
        )
    }

    private fun NavDestination?.hasTopScreenAs(route: KClass<*>): Boolean {
        val topDestination = this?.hierarchy?.firstOrNull() ?: return false
        return topDestination.hasRoute(route)
    }

    private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
        this?.hierarchy?.any { dest ->
            dest.hasRoute(route)
        } == true
}