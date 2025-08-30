package com.github.abhiram0106.lobotus_assignment.feature_companies.di

import com.github.abhiram0106.lobotus_assignment.feature_companies.CompaniesRepositoryImpl
import com.github.abhiram0106.lobotus_assignment.feature_companies.data.CompaniesServiceImpl
import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.CompaniesRepository
import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.CompaniesService
import com.github.abhiram0106.lobotus_assignment.feature_companies.presentation.CompaniesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val companyModule = module {
    single<CompaniesService> {
        CompaniesServiceImpl(httpClient = get())
    }
    single<CompaniesRepository> {
        CompaniesRepositoryImpl(companiesService = get())
    }
    viewModel {
        CompaniesViewModel(companiesRepository = get())
    }
}