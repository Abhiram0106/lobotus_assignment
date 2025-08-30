package com.github.abhiram0106.lobotus_assignment.feature_companies.presentation.state_and_actions

import com.github.abhiram0106.lobotus_assignment.core.util.UiText

data class CompanyScreenUiState(
    val searchQuery: String = "",
    val snackBarMessage: UiText? = null
)
