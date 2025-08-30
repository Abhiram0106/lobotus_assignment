package com.github.abhiram0106.lobotus_assignment.feature_companies.domain

import com.github.abhiram0106.lobotus_assignment.feature_companies.data.model.CompaniesResponse
import com.github.abhiram0106.lobotus_assignment.feature_companies.data.model.CompanyResponse
import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.model.CompanyData

fun CompanyResponse.toCompanyData(): CompanyData = CompanyData(
    id = clientID.toString(),
    companyName = companyName,
    contactPersonName = contactPersonName,
    phoneNumber = phoneNumber.toString(),
    email = email,
    latitude = clientLoc.latitude,
    longitude = clientLoc.longitude
)

fun List<CompanyResponse>.toCompanyList(): List<CompanyData> = map { it.toCompanyData() }

fun CompaniesResponse.toCompaniesData(): List<CompanyData> = this.companies.toCompanyList()

fun Result<CompaniesResponse>.toDomain(): Result<List<CompanyData>> = map { it.toCompaniesData() }