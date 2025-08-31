package com.github.abhiram0106.lobotus_assignment.preview.feature_home

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.abhiram0106.lobotus_assignment.core.ui.theme.AppTheme
import com.github.abhiram0106.lobotus_assignment.feature_home.presentation.components.QuickActionItem
import com.github.abhiram0106.lobotus_assignment.R

@Preview(showBackground = true)
@Composable
private fun QuickActionItemPreview() {
    AppTheme(false) {
        QuickActionItem(
            painter = painterResource(R.drawable.ic_location),
            text = "My Visit"
        )
    }
}