package com.github.abhiram0106.lobotus_assignment.feature_companies.domain.model

data class CompanyData(
    val id: String,
    val companyName: String,
    val contactPersonName: String,
    val phoneNumber: String,
    val email: String,
    val latitude: String?,
    val longitude: String?
)
