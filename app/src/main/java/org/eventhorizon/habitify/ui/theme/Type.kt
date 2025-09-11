package org.eventhorizon.habitify.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.eventhorizon.habitify.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

private val manropeFont = FontFamily(
    Font(R.font.manrope_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.manrope_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.manrope_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.manrope_semibold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.manrope_extrabold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.manrope_extralight, FontWeight.ExtraLight, FontStyle.Normal),
    Font(R.font.manrope_light, FontWeight.Light, FontStyle.Normal)
)

private val nunitoFont = FontFamily(
    Font(R.font.nunito_black, weight = FontWeight.ExtraBold, style = FontStyle.Normal)
)

val onbTitleTextStyle = TextStyle(
    fontFamily = nunitoFont,
    fontWeight = FontWeight.Bold,
    fontSize = 26.sp,
    letterSpacing = 0.sp,
    color = AppColor.OnbTextDarkPurple
)

val onbSubtitleTextStyle = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
    letterSpacing = 0.sp,
    color = AppColor.OnbTextDarkPurple
)

val onbSkipNextTextStyle = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    letterSpacing = 0.sp,
    color = AppColor.OnbTextDarkPurple
)



