package com.github.abhiram0106.lobotus_assignment.core.navigation.nav_graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.github.abhiram0106.lobotus_assignment.core.navigation.Graphs
import com.github.abhiram0106.lobotus_assignment.core.navigation.Screens
import com.github.abhiram0106.lobotus_assignment.core.util.UiText
import com.github.abhiram0106.lobotus_assignment.feature_companies.presentation.CompaniesRoute

fun NavGraphBuilder.companiesGraph(
    onScrollChange: (isScrollingUp: Boolean) -> Unit,
    onShowSnackBar: suspend (message: UiText, actionLabel: UiText?) -> Boolean,
) {

    navigation<Graphs.Companies>(startDestination = Screens.Companies) {
        composable<Screens.Companies> { entry ->
            CompaniesRoute(
                onScrollChange = onScrollChange,
                onShowSnackBar = onShowSnackBar
            )
        }
    }
}
