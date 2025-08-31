package com.github.abhiram0106.lobotus_assignment.feature_companies.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import com.github.abhiram0106.lobotus_assignment.R
import com.github.abhiram0106.lobotus_assignment.core.presentation.components.MyUnderlinedTitle

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MotionLayoutCompaniesTitleBar(
    modifier: Modifier = Modifier,
    progress: Float,
    searchQuery: String,
    onSearch: (String) -> Unit,
    onClickFilters: () -> Unit,
    onClickNearByClients: () -> Unit,
) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene_companies)
            .readBytes().decodeToString()
    }
    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = modifier.fillMaxWidth()
    ) {
        MyUnderlinedTitle(
            title = stringResource(R.string.companies),
            modifier = Modifier.layoutId("company")
        )
        TextButton(
            onClick = onClickNearByClients,
            modifier = Modifier.layoutId("nearby")
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
            modifier = Modifier.layoutId("search")
        )

        IconButton(
            onClick = onClickFilters,
            modifier = Modifier.layoutId("filter")
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_filter),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}