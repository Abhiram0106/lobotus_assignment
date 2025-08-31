package com.github.abhiram0106.lobotus_assignment.feature_home.presentation.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene


@OptIn(ExperimentalMotionApi::class)
@Composable
fun CollapsingToolbar() {
    // State to track if the layout is expanded or collapsed
    var isExpanded by remember { mutableStateOf(false) }

    // Animate the progress of the motion layout
    val progress by animateFloatAsState(
        targetValue = if (isExpanded) 1f else 0f,
        animationSpec = tween(700),
        label = "motion_progress"
    )

    // The MotionScene defining the start and end states
    val motionScene = MotionScene {
        val headerRef = createRefFor("header")
        val logoRef = createRefFor("logo")
        val greetingRef = createRefFor("greeting")
        val statusRef = createRefFor("status")
        val durationRef = createRefFor("duration")
        val clockImageRef = createRefFor("clock_image")
        val mainContentRef = createRefFor("main_content")
        val checkoutButtonRef = createRefFor("checkout_button")
        val bottomBarRef = createRefFor("bottom_bar")
        val checkinTimeRef = createRefFor("checkin_time")
        val checkinLocationRef = createRefFor("checkin_location")
        val baseLocationRef = createRefFor("base_location")

        val startConstraintSet = constraintSet {
            constrain(headerRef) {
                width = Dimension.fillToConstraints
                height = Dimension.value(200.dp)
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(logoRef) {
                top.linkTo(parent.top, 32.dp)
                start.linkTo(parent.start, 16.dp)
            }
            constrain(greetingRef) {
                top.linkTo(logoRef.bottom, 8.dp)
                start.linkTo(parent.start, 16.dp)
            }
            constrain(statusRef) {
                top.linkTo(greetingRef.bottom, 4.dp)
                start.linkTo(parent.start, 16.dp)
            }
            constrain(durationRef) {
                alpha = 0f // Hidden in start state
            }
            constrain(clockImageRef) {
                top.linkTo(parent.top, 24.dp)
                end.linkTo(parent.end, 16.dp)
                alpha = 1f
            }
            constrain(mainContentRef) {
                width = Dimension.fillToConstraints
                top.linkTo(headerRef.bottom)
                bottom.linkTo(checkoutButtonRef.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(checkoutButtonRef) {
                width = Dimension.fillToConstraints
                bottom.linkTo(bottomBarRef.top, 16.dp)
                start.linkTo(parent.start, 16.dp)
                end.linkTo(parent.end, 16.dp)
            }
            constrain(bottomBarRef) {
                width = Dimension.fillToConstraints
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(checkinTimeRef) { alpha = 0f }
            constrain(checkinLocationRef) { alpha = 0f }
            constrain(baseLocationRef) { alpha = 0f }
        }

        val endConstraintSet = constraintSet {
            constrain(headerRef) {
                width = Dimension.fillToConstraints
                height = Dimension.value(120.dp)
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(logoRef) {
                alpha = 0f // Hidden in end state
                top.linkTo(parent.top, 32.dp)
                start.linkTo(parent.start, 16.dp)
            }
            constrain(greetingRef) {
                top.linkTo(parent.top, 40.dp)
                start.linkTo(parent.start, 16.dp)
            }
            constrain(statusRef) {
                alpha = 0f // Hidden in end state
            }
            constrain(durationRef) {
                alpha = 1f
                top.linkTo(greetingRef.bottom, 4.dp)
                start.linkTo(parent.start, 16.dp)
            }
            constrain(clockImageRef) {
                top.linkTo(parent.top, 24.dp)
                end.linkTo(parent.end, 16.dp)
                alpha = 0.5f
            }
            constrain(checkoutButtonRef) {
                width = Dimension.fillToConstraints
                top.linkTo(headerRef.bottom, 16.dp)
                start.linkTo(parent.start, 16.dp)
                end.linkTo(parent.end, 16.dp)
            }
            constrain(mainContentRef) {
                width = Dimension.fillToConstraints
                top.linkTo(baseLocationRef.bottom, 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(bottomBarRef) {
                alpha = 0f // Hidden in end state
            }
            constrain(checkinTimeRef) {
                alpha = 1f
                top.linkTo(checkoutButtonRef.bottom, 24.dp)
                start.linkTo(parent.start, 24.dp)
            }
            constrain(checkinLocationRef) {
                alpha = 1f
                top.linkTo(checkinTimeRef.bottom, 24.dp)
                start.linkTo(parent.start, 16.dp)
            }
            constrain(baseLocationRef) {
                alpha = 1f
                top.linkTo(checkinLocationRef.bottom, 8.dp)
                start.linkTo(parent.start, 16.dp)
            }
        }

        transition(startConstraintSet, endConstraintSet, "swipe") {
            // No keyframes needed for this simple transition,
            // MotionLayout will interpolate automatically.
        }
    }

    MotionLayout(
        motionScene = motionScene,
        progress = progress,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F8))
    ) {
        // Header Section
        Box(
            modifier = Modifier
                .layoutId("header")
                .fillMaxWidth()
                .background(
                    Color(0xFFF0F0F8)
                )
        )

        // Using a generic icon as a placeholder for the MI logo
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "MI Logo",
            tint = Color(0xFF6200EE),
            modifier = Modifier
                .layoutId("logo")
                .size(40.dp)
        )

        Text(
            text = "Hi Demo!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.layoutId("greeting")
        )

        Text(
            text = "You are On duty...",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.layoutId("status")
        )

        Text(
            text = "0h 7m",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.layoutId("duration")
        )

        // Using a generic icon as a placeholder for the clock image
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Clock",
            tint = Color.LightGray,
            modifier = Modifier
                .layoutId("clock_image")
                .size(100.dp)
        )


        // Main Content Area
        Column(modifier = Modifier.layoutId("main_content")) {
            // In start state this shows, in end state it is replaced by another list
            if (progress < 0.5f) {
                QuickActionsSection(
                    title = "Quick Actions",
                    actions = startActions,
                    icon = Icons.Default.Person
                )
                CurrentLocationSection()
            } else {
                QuickActionsSection(
                    title = "Quick Actions",
                    actions = endActions,
                    isScrollable = true
                )
            }
        }

        // Check Out Button - a central element in the animation
        Button(
            onClick = { isExpanded = !isExpanded },
            modifier = Modifier
                .layoutId("checkout_button")
                .height(56.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
        ) {
            Text(text = if (progress < 0.5f) "Check Out" else "Check Out", fontSize = 18.sp)
        }

        // Bottom Bar (only visible in start state)
        BottomBar(modifier = Modifier.layoutId("bottom_bar"))

        // Check-in time and location (only visible in end state)
        Column(modifier = Modifier.layoutId("checkin_time")){
            Text("Check In Time", color = Color.Gray, fontSize = 14.sp)
            Text("04:35 pm", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.Black)
        }
        Text("Check In Location", modifier = Modifier.layoutId("checkin_location"), color = Color.Gray)
        Text("2 AnjaneyaBuilding 5 Loop Road, Race Course Rd...", modifier = Modifier.layoutId("base_location"), color = Color.Black)
    }
}

// --- Helper Data and Composables ---

data class ActionItem(val icon: ImageVector, val label: String)

val startActions = listOf(
    ActionItem(Icons.Default.Person, "My Visit"),
    ActionItem(Icons.Default.Person, "Direct"),
    ActionItem(Icons.Default.Person, "Schedule")
)

val endActions = listOf(
    ActionItem(Icons.Default.Person, "Chats"),
    ActionItem(Icons.Default.Person, "Route"),
    ActionItem(Icons.Default.Person, "My Visit"),
    ActionItem(Icons.Default.Person, "Direct"),
    ActionItem(Icons.Default.Person, "Jobs"),
    ActionItem(Icons.Default.Person, "Tour"),
    ActionItem(Icons.Default.Person, "Add Client"),
    ActionItem(Icons.Default.Person, "Complaints")
)

@Composable
fun QuickActionsSection(
    title: String,
    actions: List<ActionItem>,
    icon: ImageVector? = null,
    isScrollable: Boolean = false
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(8.dp))
            icon?.let {
                Icon(imageVector = it, contentDescription = "Action Menu")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        if(isScrollable) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(actions) { action ->
                    ActionItemView(action.icon, action.label)
                }
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                actions.forEach { action ->
                    ActionItemView(action.icon, action.label)
                }
            }
        }
    }
}

@Composable
fun ActionItemView(icon: ImageVector, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(80.dp)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = label, tint = Color(0xFF6200EE))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, fontSize = 12.sp, textAlign = TextAlign.Center)
    }
}

@Composable
fun CurrentLocationSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text("Current Location", color = Color(0xFFDA3A3A), fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "2 AnjaneyaBuilding 5 Loop Road, Race Course Rd, Racecourse, Gandhi Nagar, Bengaluru, Karnataka 560009, India",
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    }
}

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        InfoColumn("04:35 pm", Icons.Default.Person)
        InfoColumn("Check Out", Icons.Default.Person, isMain = true)
        InfoColumn("0h 7m", Icons.Default.Person)
    }
}

@Composable
fun InfoColumn(text: String, icon: ImageVector, isMain: Boolean = false) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = if(isMain) Color.Black else Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text,
            color = if(isMain) Color.Black else Color.Gray,
            fontWeight = if(isMain) FontWeight.Bold else FontWeight.Normal,
            fontSize = 14.sp
        )
    }
}
