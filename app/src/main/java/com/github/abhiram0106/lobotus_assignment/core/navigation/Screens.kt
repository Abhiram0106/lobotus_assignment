package com.github.abhiram0106.lobotus_assignment.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Graphs {

    @Serializable
    data object Home

    @Serializable
    data object Companies

    @Serializable
    data object Settings
}

@Serializable
sealed class Screens {
    @Serializable
    data object Home

    @Serializable
    data object Companies

    @Serializable
    data object Settings
}
