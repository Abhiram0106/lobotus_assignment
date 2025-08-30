package com.github.abhiram0106.lobotus_assignment.core.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MyUnderlinedTitle(
    modifier: Modifier = Modifier,
    title: String,
    style: TextStyle = MaterialTheme.typography.headlineMedium
) {

    val density = LocalDensity.current
    var dividerWidth by remember {
        mutableStateOf(5.dp)
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            style = style,
            fontWeight = FontWeight.Bold,
            onTextLayout = {
                dividerWidth = with(density) { it.size.width.toDp() / 2 }
            }
        )
        Spacer(modifier = Modifier.height(2.dp))
        HorizontalDivider(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.width(dividerWidth)
        )
    }
}