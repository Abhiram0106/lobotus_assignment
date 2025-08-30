package com.github.abhiram0106.lobotus_assignment.feature_companies.domain

import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.model.CompanyData

interface CompaniesRepository {

    suspend fun getCompanies(userId: String, pageSize: Int, currentPage: Int, enabledStatus: Int):
            Result<List<CompanyData>>
}