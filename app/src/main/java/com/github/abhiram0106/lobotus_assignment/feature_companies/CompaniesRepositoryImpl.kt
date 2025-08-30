package com.github.abhiram0106.lobotus_assignment.feature_companies

import android.util.Log
import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.CompaniesRepository
import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.CompaniesService
import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.model.CompanyData
import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.toDomain

class CompaniesRepositoryImpl(private val companiesService: CompaniesService) :
    CompaniesRepository {
    override suspend fun getCompanies(
        searchQuery: String,
        pageSize: Int,
        currentPage: Int
    ): Result<List<CompanyData>> {
        return companiesService.getCompanies(
            searchQuery = searchQuery,
            userId = "USR5021",
            pageSize = pageSize,
            currentPage = currentPage,
            enabledStatus = 1
        ).toDomain()
    }
}