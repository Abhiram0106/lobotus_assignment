package com.github.abhiram0106.lobotus_assignment.feature_companies.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CompaniesResponse(
    @SerialName("result") val companies: List<CompanyResponse>
)

@Serializable
data class CompanyResponse(
    val clientID: Int,
    @SerialName("clientName") val companyName: String,
    val contactPersonName: String,
    @SerialName("mobile") val phoneNumber: Long,
    val email: String,
    val clientLoc: ClientLocation
)

@Serializable
data class ClientLocation(
    val latitude: String? = null,
    val longitude: String? = null
)