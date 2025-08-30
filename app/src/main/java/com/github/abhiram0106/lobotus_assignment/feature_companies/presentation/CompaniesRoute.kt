package com.github.abhiram0106.lobotus_assignment.feature_companies.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.abhiram0106.lobotus_assignment.core.util.UiText

@Composable
fun CompaniesRoute(
    onShowSnackBar: suspend (message: UiText, actionLabel: UiText?) -> Boolean,

    ) {

    Text(text = "COMPANIES")
}

@Composable
fun CompaniesScreen(modifier: Modifier = Modifier) {

}
