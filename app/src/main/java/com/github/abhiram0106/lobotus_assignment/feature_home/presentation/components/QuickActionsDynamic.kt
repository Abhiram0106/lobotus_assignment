package com.github.abhiram0106.lobotus_assignment.feature_home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.abhiram0106.lobotus_assignment.R

@Composable
fun QuickActionsDynamic(
    modifier: Modifier = Modifier,
    quickActions: List<Pair<String, Painter>> = listOf(
        "Chats" to painterResource(R.drawable.ic_chat),
        "Route" to painterResource(R.drawable.ic_location),
        "My Visit" to painterResource(R.drawable.ic_location),
        "Direct" to painterResource(R.drawable.ic_location),
        "Schedule" to painterResource(R.drawable.ic_schedule),
        "Attendance" to painterResource(R.drawable.ic_schedule),
        "Add Client" to painterResource(R.drawable.ic_person),
        "Companies" to painterResource(R.drawable.ic_groups_outlined),
    ),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.quick_actions),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(25.dp),
            contentPadding = PaddingValues(horizontal = 25.dp)
        ) {
            items(quickActions.size) {
                quickActions[it].let { (text, painter) ->
                    QuickActionItem(
                        painter = painter,
                        text = text
                    )
                }
            }
        }
    }
}