package com.github.abhiram0106.lobotus_assignment.preview.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.abhiram0106.lobotus_assignment.core.presentation.components.MyUnderlinedTitle
import com.github.abhiram0106.lobotus_assignment.core.ui.theme.AppTheme

@Preview(showBackground = true)
@Composable
private fun MyUnderlinedTitlePreview() {
    AppTheme(false) {
        MyUnderlinedTitle(
            title = "Settings",
        )
    }
}