package com.github.abhiram0106.lobotus_assignment.feature_companies.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.abhiram0106.lobotus_assignment.R
import com.github.abhiram0106.lobotus_assignment.core.util.UiText
import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.model.CompanyData
import com.github.abhiram0106.lobotus_assignment.feature_companies.presentation.components.CompaniesTitleBarExpanded
import com.github.abhiram0106.lobotus_assignment.feature_companies.presentation.components.CompanyListItem
import com.github.abhiram0106.lobotus_assignment.feature_companies.presentation.state_and_actions.CompanyScreenUiAction
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun CompaniesRoute(
    viewModel: CompaniesViewModel = koinViewModel(),
    onShowSnackBar: suspend (message: UiText, actionLabel: UiText?) -> Boolean,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage) {
        if (uiState.snackBarMessage != null) {
            onShowSnackBar(uiState.snackBarMessage!!, null)
            viewModel.clearSnackBar()
        }
    }

    CompaniesScreen(
        searchQuery = uiState.searchQuery,
        companies = uiState.companies,
        onSearch = { viewModel.onUiAction(CompanyScreenUiAction.OnSearchQueryChanged(it)) },
        onClickFilters = {},
        onClickNearByClients = {},
        onNoDialerApp = {
            coroutineScope.launch {
                onShowSnackBar(UiText.StringResourceId(R.string.dialer_app_not_found), null)
            }
        },
        onNoEmailApp = {
            coroutineScope.launch {
                onShowSnackBar(UiText.StringResourceId(R.string.email_app_not_found), null)
            }
        }
    )
}

@Composable
fun CompaniesScreen(
    modifier: Modifier = Modifier,
    searchQuery: String,
    companies: List<CompanyData>,
    onSearch: (String) -> Unit,
    onClickFilters: () -> Unit,
    onClickNearByClients: () -> Unit,
    onNoDialerApp: () -> Unit,
    onNoEmailApp: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CompaniesTitleBarExpanded(
            searchQuery = searchQuery,
            onSearch = onSearch,
            onClickFilters = onClickFilters,
            onClickNearByClients = onClickNearByClients,
            modifier = Modifier.padding(vertical = 5.dp)
        )
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = companies,
                key = { it.id }
            ) {
                CompanyListItem(
                    data = it,
                    onClickReTag = {},
                    onNoEmailApp = onNoEmailApp,
                    onNoDialerApp = onNoDialerApp
                )
            }
        }
    }
}
