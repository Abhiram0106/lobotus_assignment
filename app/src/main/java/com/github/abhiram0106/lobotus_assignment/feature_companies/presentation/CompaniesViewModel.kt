package com.github.abhiram0106.lobotus_assignment.feature_companies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.abhiram0106.lobotus_assignment.core.util.UiText
import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.CompaniesRepository
import com.github.abhiram0106.lobotus_assignment.feature_companies.presentation.state_and_actions.CompanyScreenUiAction
import com.github.abhiram0106.lobotus_assignment.feature_companies.presentation.state_and_actions.CompanyScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CompaniesViewModel(
    private val companiesRepository: CompaniesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CompanyScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            companiesRepository.getCompanies("", 1, 1, 1)
                .onSuccess { data ->
                    _uiState.update { it.copy(companies = data) }
                }.onFailure { failure ->
                    _uiState.update {
                        it.copy(
                            snackBarMessage = UiText.DynamicString(
                                failure.message ?: "something went wrong"
                            )
                        )
                    }
                }
        }
    }

    fun onUiAction(action: CompanyScreenUiAction) {
        when (action) {
            is CompanyScreenUiAction.OnSearchQueryChanged -> {
                _uiState.update { it.copy(searchQuery = action.searchQuery) }
            }
        }
    }

    fun clearSnackBar() {
        _uiState.update {
            it.copy(snackBarMessage = null)
        }
    }
}