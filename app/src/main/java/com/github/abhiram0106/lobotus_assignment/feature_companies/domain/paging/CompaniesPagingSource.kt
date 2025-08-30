package com.github.abhiram0106.lobotus_assignment.feature_companies.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.abhiram0106.lobotus_assignment.core.util.AnErrorHasOccurredException
import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.CompaniesRepository
import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.model.CompanyData

class CompaniesPagingSource(
    private val searchQuery: String,
    private val repository: CompaniesRepository
) : PagingSource<Int, CompanyData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CompanyData> {
        val pageNumber = params.key ?: 1

        val response = repository.getCompanies(
            searchQuery = searchQuery,
            pageSize = params.loadSize,
            currentPage = pageNumber,
        )

        if (response.isFailure) {
            return LoadResult.Error(response.exceptionOrNull() ?: AnErrorHasOccurredException())
        }

        // data never null because isFailure check above but did this anyway
        val data = response.getOrNull().orEmpty()
        val endOfPaginationReached = data.size < params.loadSize

        return LoadResult.Page(
            data = data,
            prevKey = if (pageNumber <= 1) null else pageNumber - 1,
            nextKey = if (endOfPaginationReached) null else pageNumber + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, CompanyData>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }
}