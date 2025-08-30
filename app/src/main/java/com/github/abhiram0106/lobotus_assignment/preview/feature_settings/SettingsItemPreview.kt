package com.github.abhiram0106.lobotus_assignment.preview.feature_settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.abhiram0106.lobotus_assignment.core.ui.theme.AppTheme
import com.github.abhiram0106.lobotus_assignment.feature_settings.presentation.components.SettingsItem
import com.github.abhiram0106.lobotus_assignment.R

@Preview(showBackground = true)
@Composable
private fun SettingsItemPreview() {
    AppTheme(false) {
        SettingsItem(
            icon = R.drawable.ic_settings_outlined,
            text = {
                Column {
                    Text(text = "Track me every 1 min")
                    Text(
                        text = "something something this is a description for the SettingsItem preview",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            },
            trailingIcon = {
                Switch(checked = false, onCheckedChange = {})
            }
        )
    }
}