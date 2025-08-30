package com.github.abhiram0106.lobotus_assignment.feature_companies.presentation.state_and_actions

sealed class CompanyScreenUiAction {
    data class OnSearchQueryChanged(val searchQuery: String) : CompanyScreenUiAction()
}