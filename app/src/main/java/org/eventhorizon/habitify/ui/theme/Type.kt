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
    //мягкие толстые буковки
    Font(R.font.nunito_black, weight = FontWeight.Black, style = FontStyle.Normal),
    Font(R.font.nunito_bold, weight = FontWeight.Bold, style = FontStyle.Normal),
    Font(R.font.nunito_regular, weight = FontWeight.Bold, style = FontStyle.Normal),
)


//onboarding
val onbTitleTextStyle = TextStyle(
    fontFamily = nunitoFont,
    fontWeight = FontWeight.Black,
    fontSize = 26.sp,
    letterSpacing = 0.sp,
    color = AppColor.DarkPurple
)

val onbSubtitleTextStyle = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
    letterSpacing = 0.sp,
    color = AppColor.DarkPurple
)

val onbSkipNextTextStyle = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    letterSpacing = 0.sp,
    color = AppColor.DarkPurple
)

//topbar
val topAppBarTextStyle = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
    letterSpacing = 0.sp,
    color = AppColor.DarkPurple
)

//homescreen
val homeQuoteTextStyle = TextStyle(
    fontFamily = nunitoFont,
    fontWeight = FontWeight.Black,
    fontSize = 18.sp,
    letterSpacing = 0.sp,
    color = AppColor.DarkPurple
)
val homeQuoteAuthorTextStyle = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Bold,
    fontSize = 12.sp,
    letterSpacing = 0.sp,
    color = AppColor.homeQuoteAuthor
)
val homeDateCardDayOfWeekTextStyle = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Bold,
    fontSize = 10.sp,
    letterSpacing = 0.sp,
    lineHeight = 14.sp,
    color = AppColor.homeDatePurple50
)
val homeDateCardDateTextStyle = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    letterSpacing = (-1).sp,
    color = AppColor.homeDayOfWeekPurple
)

val homeChartTitleTextStyle = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    lineHeight = 32.sp,
    letterSpacing = 0.sp,
    color = AppColor.homeDayOfWeekPurple
)
val homeChartHabitTitleTextStyle = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    letterSpacing = 0.sp,
    color = AppColor.homeDayOfWeekPurple
)

//congrats dialog
val congratsDialogTitleTextStyle = TextStyle(
    fontFamily = nunitoFont,
    fontWeight = FontWeight.Medium,
    fontSize = 24.sp,
    lineHeight = 32.sp,
    letterSpacing = 0.sp,
    color = AppColor.DarkPurple
)
val congratsDialogSubtitleTextStyle = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    lineHeight = 22.sp,
    letterSpacing = 0.sp,
    color = AppColor.homeQuoteAuthor
)
val congratsDialogBtnTextStyle = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.sp,
    color = AppColor.dialogTitleColor
)

//habitInfo
val habitInfoTopCardTitleTextStyle = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    letterSpacing = (-1).sp,
    color = AppColor.homeDayOfWeekPurple
)
val habitInfoTopCardSubtitleTextStyle = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    lineHeight = 22.sp,
    letterSpacing = 0.sp,
    color = AppColor.homeQuoteAuthor
)
val calendarMonthTextStyle = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    letterSpacing = 0.sp,
    color = AppColor.homeDayOfWeekPurple
)
val calendarDayOfWeekTextStyle = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Bold,
    fontSize = 10.sp,
    letterSpacing = 0.sp,
    lineHeight = 14.sp,
    color = AppColor.homeDatePurple50
)
val calendarDateTextStyle = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
    letterSpacing = 0.sp,
    lineHeight = 14.sp,
    color = AppColor.DarkPurple
)
val analyticsTitleTextStyle = TextStyle(
    fontFamily = nunitoFont,
    fontWeight = FontWeight.Normal,
    fontSize = 24.sp,
    letterSpacing = 0.sp,
    color = AppColor.DarkPurple
)
val analyticsSubtitleTextStyle = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Medium,
    fontSize = 12.sp,
    letterSpacing = 0.sp,
    color = AppColor.homeDatePurple50
)
val habitInfoBtnTextStyle = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.sp,
    color = AppColor.dialogTitleColor
)

//newHabit
val textFieldTextStyle = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.sp,
)
val newHabitOrangeBright = TextStyle(
    fontFamily = manropeFont,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.sp,
    color = AppColor.OnbBtnOrange
)

