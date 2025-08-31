package com.github.abhiram0106.lobotus_assignment.feature_settings.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.github.abhiram0106.lobotus_assignment.core.presentation.components.MyUnderlinedTitle
import com.github.abhiram0106.lobotus_assignment.core.util.UiText
import com.github.abhiram0106.lobotus_assignment.R
import com.github.abhiram0106.lobotus_assignment.feature_settings.presentation.components.SettingsItem

@Composable
fun SettingsRoute(
    onShowSnackBar: suspend (message: UiText, actionLabel: UiText?) -> Boolean,
) {

    var isTrackChecked by remember {
        mutableStateOf(true)
    }
    var isRemindChecked by remember {
        mutableStateOf(false)
    }

    SettingsScreen(
        isTrackChecked = isTrackChecked,
        isRemindChecked = isRemindChecked,
        onCheckTrack = { isTrackChecked = it },
        onCheckRemind = { isRemindChecked = it },
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 10.dp, horizontal = 15.dp)
    )
}

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    isTrackChecked: Boolean,
    isRemindChecked: Boolean,
    onCheckTrack: (Boolean) -> Unit,
    onCheckRemind: (Boolean) -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        MyUnderlinedTitle(
            title = stringResource(R.string.settings)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .dropShadow(
                        CircleShape,
                        Shadow(
                            radius = 5.dp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2F)
                        )
                    )
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Demo ( 123 )",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "+91 1234567890",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7F)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        MyUnderlinedTitle(
            title = stringResource(R.string.others),
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(10.dp))
        SettingsItem(
            icon = R.drawable.ic_location,
            text = {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(R.string.base_location),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            painter = painterResource(R.drawable.ic_check_filled),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "5, Race Course Rd, Racecourse Gandhi Nagar, Bengaluru, Karnataka",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            },
        )

        Spacer(modifier = Modifier.height(20.dp))
        MyUnderlinedTitle(
            title = stringResource(R.string.settings),
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(10.dp))
        Column {
            SettingsItem(
                icon = R.drawable.ic_location,
                text = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                append(stringResource(R.string.track_me_every))
                                append(" ")
                                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.error)) {
                                    append("1 Min")
                                }
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Switch(
                            checked = isTrackChecked,
                            onCheckedChange = onCheckTrack
                        )
                    }
                }
            )
            SettingsItem(
                icon = R.drawable.ic_alarm,
                text = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                append(stringResource(R.string.remind_me))
                                append(" ")
                                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.error)) {
                                    append("30 Min")
                                }
                                append(" ")
                                append(stringResource(R.string.early))
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Switch(
                            checked = isRemindChecked,
                            onCheckedChange = onCheckRemind
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            SettingsItem(
                icon = R.drawable.ic_car,
                text = {
                    Text(
                        text = buildAnnotatedString {
                            append(stringResource(R.string.default_mot))
                            append(" ")
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.error)) {
                                append("Car")
                            }
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                modifier = Modifier.clickable {}
            )
            Spacer(modifier = Modifier.height(20.dp))
            SettingsItem(
                icon = R.drawable.ic_share,
                text = {
                    Text(
                        text = stringResource(R.string.refer_metricinfo),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                modifier = Modifier.clickable {}
            )
            Spacer(modifier = Modifier.height(20.dp))
            SettingsItem(
                icon = R.drawable.ic_theme,
                text = {
                    Text(
                        text = buildAnnotatedString {
                            append(stringResource(R.string.theme))
                            append(" ")
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.error)) {
                                append("Purple")
                            }
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                modifier = Modifier.clickable {}
            )
            Spacer(modifier = Modifier.height(20.dp))
            SettingsItem(
                icon = R.drawable.ic_logout,
                text = {
                    Text(
                        text = stringResource(R.string.logout),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                modifier = Modifier.clickable {}
            )
        }
    }
}
