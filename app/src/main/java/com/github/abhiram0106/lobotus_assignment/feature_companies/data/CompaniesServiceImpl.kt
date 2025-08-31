package com.github.abhiram0106.lobotus_assignment.feature_companies.data

import com.github.abhiram0106.lobotus_assignment.core.util.Endpoint
import com.github.abhiram0106.lobotus_assignment.core.util.handleApi
import com.github.abhiram0106.lobotus_assignment.feature_companies.data.model.CompaniesResponse
import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.CompaniesService
import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.http.HttpMethod
import io.ktor.http.appendPathSegments
import io.ktor.http.headers
import kotlinx.serialization.json.Json
import com.github.abhiram0106.lobotus_assignment.BuildConfig

class CompaniesServiceImpl(private val httpClient: HttpClient) : CompaniesService {
    override suspend fun getCompanies(
        searchQuery: String,
        userId: String,
        pageSize: Int,
        currentPage: Int,
        enabledStatus: Int
    ): Result<CompaniesResponse> {

//        val json = Json { ignoreUnknownKeys = true }
//        val data = json.decodeFromString<CompaniesResponse>(Endpoint.SAMPLE_RESPONSE)
//        return Result.success(data)

        return httpClient.handleApi {
            url {
                appendPathSegments(Endpoint.GET_COMPANIES)
                parameters.apply {
                    append(name = "Search", value = searchQuery)
                    append(name = "UserId", value = userId)
                    append(name = "PageSize", value = pageSize.toString())
                    append(name = "CurrentPage", value = currentPage.toString())
                    append(name = "EnabledStatus", value = enabledStatus.toString())
                }
                header("Travelize_Authentication", BuildConfig.TOKEN)
            }
            method = HttpMethod.Get
        }
    }
}