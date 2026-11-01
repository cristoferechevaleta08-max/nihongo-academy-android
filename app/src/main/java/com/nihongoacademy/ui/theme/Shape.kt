package com.nihongoacademy.ui.theme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp
object AcademyShapes {
 val Small=RoundedCornerShape(8.dp); val Medium=RoundedCornerShape(14.dp); val Large=RoundedCornerShape(20.dp); val ExtraLarge=RoundedCornerShape(28.dp); val Pill=RoundedCornerShape(50)
}
val AcademyMaterialShapes=Shapes(small=AcademyShapes.Small,medium=AcademyShapes.Medium,large=AcademyShapes.Large)