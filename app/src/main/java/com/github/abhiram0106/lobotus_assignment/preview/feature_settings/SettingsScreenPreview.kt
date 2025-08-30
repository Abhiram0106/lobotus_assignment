package com.github.abhiram0106.lobotus_assignment.preview.feature_settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.abhiram0106.lobotus_assignment.core.ui.theme.AppTheme
import com.github.abhiram0106.lobotus_assignment.feature_settings.presentation.SettingsScreen

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    AppTheme(false) {
        SettingsScreen(
            isTrackChecked = true,
            isRemindChecked = false,
            onCheckRemind = {},
            onCheckTrack = {}
        )
    }
}