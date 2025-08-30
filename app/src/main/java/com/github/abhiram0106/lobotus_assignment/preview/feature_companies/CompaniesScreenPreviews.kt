package com.github.abhiram0106.lobotus_assignment.preview.feature_companies

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.abhiram0106.lobotus_assignment.core.ui.theme.AppTheme
import com.github.abhiram0106.lobotus_assignment.feature_companies.domain.model.CompanyData
import com.github.abhiram0106.lobotus_assignment.feature_companies.presentation.components.CompanyListItem

@Preview(showBackground = true)
@Composable
private fun CompanyListItemPreview() {
    AppTheme(false) {
        CompanyListItem(
            data = CompanyData(
                id = "1",
                companyName = "Company name",
                contactPersonName = "contact person",
                phoneNumber = "1234567890",
                email = "email@email.com",
                latitude = "12.23",
                longitude = "34.56"
            ),
            onClickReTag = {},
            onNoEmailApp = {},
            onNoDialerApp = {}
        )
    }
}
