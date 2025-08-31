package com.github.abhiram0106.lobotus_assignment.feature_home.presentation.components

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class WavyShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width, 0f)
            lineTo(size.width, size.height * 0.85f)

//            lineTo(
//                size.width * 0.6f, size.height * 0.25f
//            )
            quadraticTo(
                size.width * 0.95f, size.height * 0.3f,
                size.width * 0.6f, size.height * 0.22f
            )

            lineTo(
                size.width * 0.25f, size.height * 0.22f
            )

//            lineTo(
//                0f, size.height * 0.2f
//            )
            quadraticTo(
                size.width * 0.12f, size.height * 0.21f,
                0f, size.height * 0.2f
            )

            close()
        }
        return Outline.Generic(path)
    }
}