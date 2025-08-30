package com.github.abhiram0106.lobotus_assignment.feature_companies

import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.CompaniesRepository
import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.CompaniesService
import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.model.CompanyData
import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.toDomain

class CompaniesRepositoryImpl(private val companiesService: CompaniesService) :
    CompaniesRepository {
    override suspend fun getCompanies(
        userId: String,
        pageSize: Int,
        currentPage: Int,
        enabledStatus: Int
    ): Result<List<CompanyData>> {
        return companiesService.getCompanies(
            userId = userId,
            pageSize = pageSize,
            currentPage = currentPage,
            enabledStatus = enabledStatus
        ).toDomain()
    }
}