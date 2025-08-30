package com.github.abhiram0106.lobotus_assignment.feature_companies.presentation.state_and_actions

import com.github.abhiram0106.lobotus_assignment.core.util.UiText
import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.model.CompanyData

data class CompanyScreenUiState(
    val searchQuery: String = "",
    val companies: List<CompanyData> = emptyList(),
    val snackBarMessage: UiText? = null
)
