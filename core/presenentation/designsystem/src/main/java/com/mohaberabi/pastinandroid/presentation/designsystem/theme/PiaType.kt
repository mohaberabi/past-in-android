package com.mohaberabi.pastinandroid.presentation.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mohaberabi.pastinandroid.presentation.designsystem.R

val Poppins = FontFamily(
    Font(
        resId = R.font.poppins_light,
        weight = FontWeight.Light
    ),
    Font(
        resId = R.font.poppins_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.poppins_medium,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.poppins_semibold,
        weight = FontWeight.SemiBold
    ),
    Font(
        resId = R.font.poppins_bold,
        weight = FontWeight.Bold
    ),
)

data class PiaTextTheme(
    private val font: FontFamily = Poppins,
    private val isDark: Boolean = false,
) {
    private val basePiaTextStyle = TextStyle(
        fontFamily = font,
        color = if (isDark) Color.White else Color.Black
    )
    val typography = Typography(
        bodySmall = basePiaTextStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 20.sp,
        ),
        bodyMedium = basePiaTextStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 22.sp,
        ),
        bodyLarge = basePiaTextStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp,
        ),
        labelLarge = basePiaTextStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 24.sp,
        ),
        headlineMedium = basePiaTextStyle.copy(
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
        ),
    )
}