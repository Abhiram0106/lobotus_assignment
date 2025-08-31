package com.github.abhiram0106.lobotus_assignment.feature_home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.github.abhiram0106.lobotus_assignment.R
import com.github.abhiram0106.lobotus_assignment.core.util.UiText
import com.github.abhiram0106.lobotus_assignment.feature_home.presentation.components.BottomActionsItemBar
import com.github.abhiram0106.lobotus_assignment.feature_home.presentation.components.CurrentLocationText
import com.github.abhiram0106.lobotus_assignment.feature_home.presentation.components.QuickActionStatic
import com.github.abhiram0106.lobotus_assignment.feature_home.presentation.components.QuickActionsDynamic
import com.github.abhiram0106.lobotus_assignment.feature_home.presentation.components.WavyShape

@Composable
fun HomeRoute(
    onShowSnackBar: suspend (message: UiText, actionLabel: UiText?) -> Boolean,

    ) {

    HomeScreen(
        name = "Demo",
        dutyDuration = "0h 7m",
        checkInTime = "04:35 pm",
        checkInLocation = "5, Race Course Rd, Racecourse Gandhi Nagar, Bengaluru, Karnataka",
        baseLocation = "5, Race Course Rd, Racecourse Gandhi Nagar, Bengaluru, Karnataka"
    )
}

@OptIn(ExperimentalMotionApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    name: String,
    dutyDuration: String,
    checkInTime: String,
    checkInLocation: String,
    baseLocation: String,
) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene_home)
            .readBytes().decodeToString()
    }
    var progress by remember { mutableFloatStateOf(0f) }

    val dragState = rememberDraggableState { delta ->
        val newProgress = (progress - delta / 500f) // 500 = sensitivity
            .coerceIn(0f, 1f)
        progress = newProgress
    }

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = modifier
            .fillMaxSize()
            .draggable(
                orientation = Orientation.Vertical,
                state = dragState
            )
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainer,
                    shape = WavyShape()
                )
                .layoutId("backgroundWavyShape")
        )

        Text(
            text = stringResource(R.string.metricinfo),
            style = TextStyle(
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                brush = Brush.linearGradient(colors = listOf(Color(0xFF6A1B9A), Color(0xFFE57373)))
            ),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.layoutId("metricInfo")
        )
        Icon(
            painter = painterResource(R.drawable.ic_notifications),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.layoutId("iconNotificationBell")
        )
        Icon(
            painter = painterResource(R.drawable.ic_more_vert),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.layoutId("iconMoreVert")
        )
        Image(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier.layoutId("imageLogo")
        )
        Image(
            painter = painterResource(R.drawable.ic_chair),
            contentDescription = null,
            modifier = Modifier.layoutId("imageChair")
        )
        Text(
            text = buildAnnotatedString {
                append("Hi $name!")
                append("\n")
                withStyle(
                    style = SpanStyle(
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    append(stringResource(R.string.you_are_on_duty))
                }
            },
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.layoutId("textHiDemoYouAreOnDuty")
        )
        Text(
            text = dutyDuration,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.layoutId("textDutyDuration")
        )

        Button(
            onClick = {},
            modifier = Modifier.layoutId("buttonCheckOut")
        ) {
            Text(
                text = stringResource(R.string.check_out),
            )
        }
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = MaterialTheme.typography.bodyMedium.fontSize)) {
                    append(stringResource(R.string.check_in_time))
                }
                append("\n")
                withStyle(
                    style = SpanStyle(
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(checkInTime)
                }
                append("\n")
                append("\n")
                withStyle(
                    style = SpanStyle(
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = MaterialTheme.colorScheme.primary
                    )
                ) {
                    append(stringResource(R.string.check_in_location))
                }
                append("\n")
                withStyle(style = SpanStyle(fontSize = MaterialTheme.typography.bodySmall.fontSize)) {
                    append(checkInLocation)
                }
                append("\n")
                withStyle(
                    style = SpanStyle(
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        color = MaterialTheme.colorScheme.primary
                    )
                ) {
                    append(stringResource(R.string.base_location))
                }
                append("\n")
                withStyle(style = SpanStyle(fontSize = MaterialTheme.typography.bodySmall.fontSize)) {
                    append(baseLocation)
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.85F)
                .layoutId("textCheckInTimeLocationBaseLocation")
        )

        QuickActionStatic(modifier = Modifier.layoutId("rowQuickActionsStatic"))

        CurrentLocationText(
            currentLocation = checkInLocation,
            modifier = Modifier.layoutId("textCurrentLocation")
        )

        BottomActionsItemBar(
            clockInTime = checkInTime,
            clockInDuration = dutyDuration,
            modifier = Modifier.layoutId("rowBottomActions")
        )

        QuickActionsDynamic(
            modifier = Modifier.layoutId("rowQuickActionsDynamic")
        )
    }
}