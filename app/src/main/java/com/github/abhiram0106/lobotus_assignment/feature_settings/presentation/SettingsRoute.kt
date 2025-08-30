package com.github.abhiram0106.lobotus_assignment.feature_settings.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.abhiram0106.lobotus_assignment.core.util.UiText

@Composable
fun SettingsRoute(
    onShowSnackBar: suspend (message: UiText, actionLabel: UiText?) -> Boolean,

    ) {

    Text(text = "SETTINGS")
}

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {

}
