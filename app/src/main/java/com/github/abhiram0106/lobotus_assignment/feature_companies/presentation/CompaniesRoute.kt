package com.github.abhiram0106.lobotus_assignment.feature_companies.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.github.abhiram0106.lobotus_assignment.R
import com.github.abhiram0106.lobotus_assignment.core.util.UiText
import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.model.CompanyData
import com.github.abhiram0106.lobotus_assignment.feature_companies.presentation.components.CompaniesTitleBarExpanded
import com.github.abhiram0106.lobotus_assignment.feature_companies.presentation.components.CompanyListItem
import com.github.abhiram0106.lobotus_assignment.feature_companies.presentation.state_and_actions.CompanyScreenUiAction
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun CompaniesRoute(
    viewModel: CompaniesViewModel = koinViewModel(),
    onScrollChange: (isScrollingUp: Boolean) -> Unit,
    onShowSnackBar: suspend (message: UiText, actionLabel: UiText?) -> Boolean,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val companies = viewModel.companiesFlow.collectAsLazyPagingItems()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage) {
        if (uiState.snackBarMessage != null) {
            onShowSnackBar(uiState.snackBarMessage!!, null)
            viewModel.clearSnackBar()
        }
    }

    LaunchedEffect(companies.loadState) {
        val errorMessage = if (companies.loadState.refresh is LoadState.Error) {
            (companies.loadState.refresh as LoadState.Error).error.message
        } else if (companies.loadState.append is LoadState.Error) {
            (companies.loadState.append as LoadState.Error).error.message
        } else {
            null
        }

        if (errorMessage != null) {
            onShowSnackBar(UiText.DynamicString(errorMessage), null)
            viewModel.clearSnackBar()
        }
    }

    CompaniesScreen(
        searchQuery = uiState.searchQuery,
        companies = companies,
        onSearch = { viewModel.onUiAction(CompanyScreenUiAction.OnSearchQueryChanged(it)) },
        onClickFilters = {},
        onClickNearByClients = {},
        onScrollChange = onScrollChange,
        onNoDialerApp = {
            coroutineScope.launch {
                onShowSnackBar(UiText.StringResourceId(R.string.dialer_app_not_found), null)
            }
        },
        onNoEmailApp = {
            coroutineScope.launch {
                onShowSnackBar(UiText.StringResourceId(R.string.email_app_not_found), null)
            }
        },
    )
}

@OptIn(FlowPreview::class)
@Composable
fun CompaniesScreen(
    modifier: Modifier = Modifier,
    searchQuery: String,
    companies: LazyPagingItems<CompanyData>,
    onSearch: (String) -> Unit,
    onClickFilters: () -> Unit,
    onClickNearByClients: () -> Unit,
    onNoDialerApp: () -> Unit,
    onNoEmailApp: () -> Unit,
    onScrollChange: (isScrollingUp: Boolean) -> Unit,
) {

    val listState = rememberLazyListState()
    LaunchedEffect(listState) {
        snapshotFlow { listState.lastScrolledBackward }
            .debounce(50.milliseconds)
            .distinctUntilChanged()
            .collectLatest { onScrollChange(it) }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CompaniesTitleBarExpanded(
            searchQuery = searchQuery,
            onSearch = onSearch,
            onClickFilters = onClickFilters,
            onClickNearByClients = onClickNearByClients,
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 20.dp)
        )
        LazyColumn(
            state = listState,
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(start = 10.dp, end = 10.dp, bottom = 80.dp)
        ) {
            if (companies.loadState.refresh is LoadState.Loading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.onBackground)
                    }
                }
            }

            if ((companies.loadState.refresh !is LoadState.Loading) &&
                (companies.loadState.append !is LoadState.Loading) &&
                companies.itemCount == 0
            ) {
                item {
                    Text(
                        text = stringResource(R.string.nothing_found),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    )
                }
            }

            items(
                count = companies.itemCount,
            ) {
                companies[it]?.let { data ->
                    CompanyListItem(
                        data = data,
                        onClickReTag = {},
                        onNoEmailApp = onNoEmailApp,
                        onNoDialerApp = onNoDialerApp
                    )
                }
            }
        }
    }
}
