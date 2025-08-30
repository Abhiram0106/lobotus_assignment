package com.github.abhiram0106.lobotus_assignment.feature_companies.domain

import com.github.abhiram0106.lobotus_assignment.feature_companies.data.model.CompaniesResponse

interface CompaniesService {
    suspend fun getCompanies(searchQuery: String, userId: String, pageSize: Int, currentPage: Int, enabledStatus: Int):
            Result<CompaniesResponse>
}