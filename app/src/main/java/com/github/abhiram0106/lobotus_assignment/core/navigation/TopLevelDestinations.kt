package com.github.abhiram0106.lobotus_assignment.core.navigation

import androidx.annotation.DrawableRes
import com.github.abhiram0106.lobotus_assignment.R
import com.github.abhiram0106.lobotus_assignment.core.util.UiText
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

enum class TopLevelDestination(
    @DrawableRes val icon: Int,
    @DrawableRes val unselectedIcon: Int,
    val displayText: UiText,
    val route: @Serializable KClass<*>,
    val baseRoute: @Serializable KClass<*>
) {
    Home(
        icon = R.drawable.ic_home_filled,
        unselectedIcon = R.drawable.ic_home_outlined,
        displayText = UiText.StringResourceId(R.string.home),
        route = Screens.Home::class,
        baseRoute = Graphs.Home::class
    ),
    Companies(
        icon = R.drawable.ic_groups_filled,
        unselectedIcon = R.drawable.ic_groups_outlined,
        displayText = UiText.StringResourceId(R.string.companies),
        route = Screens.Companies::class,
        baseRoute = Graphs.Companies::class
    ),
    Settings(
        icon = R.drawable.ic_settings_filled,
        unselectedIcon = R.drawable.ic_settings_outlined,
        displayText = UiText.StringResourceId(R.string.settings),
        route = Screens.Settings::class,
        baseRoute = Graphs.Settings::class
    )
}