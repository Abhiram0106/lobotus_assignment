package com.github.abhiram0106.lobotus_assignment.feature_companies.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.abhiram0106.lobotus_assignment.core.presentation.components.MyUnderlinedTitle
import com.github.abhiram0106.lobotus_assignment.R

@Composable
fun CompaniesTitleBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MyUnderlinedTitle(
            title = stringResource(R.string.companies)
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15F),
                    shape = CircleShape
                )
                .padding(10.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Icon(
            painter = painterResource(R.drawable.ic_filter),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
fun CompaniesTitleBarExpanded(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearch: (String) -> Unit,
    onClickFilters: () -> Unit,
    onClickNearByClients: () -> Unit,
) {
    Column(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MyUnderlinedTitle(
                title = stringResource(R.string.companies)
            )
            TextButton(
                onClick = onClickNearByClients
            ) {
                Text(
                    text = stringResource(R.string.near_by_clients),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    painter = painterResource(R.drawable.ic_location),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = searchQuery,
                onValueChange = onSearch,
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.search_dot_dot_dot),
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                shape = CircleShape,
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(10.dp))
            IconButton(
                onClick = onClickFilters
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_filter),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}
