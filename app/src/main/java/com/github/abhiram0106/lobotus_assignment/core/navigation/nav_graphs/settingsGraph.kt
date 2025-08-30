package com.github.abhiram0106.lobotus_assignment.core.navigation.nav_graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.github.abhiram0106.lobotus_assignment.core.util.UiText
import com.github.abhiram0106.lobotus_assignment.core.navigation.Graphs
import com.github.abhiram0106.lobotus_assignment.core.navigation.Screens
import com.github.abhiram0106.lobotus_assignment.feature_settings.presentation.SettingsRoute

fun NavGraphBuilder.settingsGraph(
    navController: NavHostController,
    onShowSnackBar: suspend (message: UiText, actionLabel: UiText?) -> Boolean,
) {

    navigation<Graphs.Settings>(startDestination = Screens.Settings) {
        composable<Screens.Settings> { entry ->
            SettingsRoute(onShowSnackBar = onShowSnackBar)
        }
    }
}
