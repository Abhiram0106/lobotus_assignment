package com.github.abhiram0106.lobotus_assignment.feature_companies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.github.abhiram0106.lobotus_assignment.core.util.UiText
import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.CompaniesRepository
import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.paging.CompaniesPagingSource
import com.github.abhiram0106.lobotus_assignment.feature_companies.presentation.state_and_actions.CompanyScreenUiAction
import com.github.abhiram0106.lobotus_assignment.feature_companies.presentation.state_and_actions.CompanyScreenUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class CompaniesViewModel(
    private val companiesRepository: CompaniesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CompanyScreenUiState())
    val uiState = _uiState.asStateFlow()

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

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val companiesFlow = _uiState
        .map { it.searchQuery }
        .debounce(500.milliseconds)
        .distinctUntilChanged()
        .flatMapLatest { searchQuery ->
            Pager(
                config = PagingConfig(
                    pageSize = 10,
                    initialLoadSize = 10
                ),
                pagingSourceFactory = {
                    CompaniesPagingSource(
                        searchQuery = searchQuery,
                        repository = companiesRepository
                    )
                }
            ).flow
        }.cachedIn(viewModelScope)
}