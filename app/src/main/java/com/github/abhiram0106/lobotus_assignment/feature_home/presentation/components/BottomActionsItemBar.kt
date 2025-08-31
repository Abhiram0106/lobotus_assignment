package com.github.abhiram0106.lobotus_assignment.feature_home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.github.abhiram0106.lobotus_assignment.R

@Composable
fun BottomActionsItemBar(
    modifier: Modifier = Modifier,
    clockInTime: String,
    clockInDuration: String,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BottomActionItem(
            painter = painterResource(R.drawable.ic_login),
            text = clockInTime
        )
        BottomActionItem(
            painter = painterResource(R.drawable.ic_logout),
            painterTint = MaterialTheme.colorScheme.error,
            text = stringResource(R.string.logout)
        )
        BottomActionItem(
            painter = painterResource(R.drawable.ic_alarm),
            text = clockInDuration
        )
    }
}