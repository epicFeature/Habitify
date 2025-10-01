package org.eventhorizon.habitify.ui.components.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

object AppColor {
    val White = Color(0xFFFFFFFF)

    //onboarding
    val OnbBgWhite = Color(0xFFFFFFFF)
    val DarkPurple = Color(0xFF573353)
    val OnbTextOrange = Color(0xFFFC9D45) //Todo Унифицировать и уменьшить кол-во цветов
    val OnbDotOrange = Color(0xFFF9B566)
    val OnbDotPurple = DarkPurple
    val OnbDotPurpleShadow = Color(0x33573353)
    val OnbBtnOrange = Color(0xFFFDA758)
    val OnbBtnDisabledOrange = Color(0xFFFFF3E9)
    val OnbBtnDisablePurpleText = Color(0x99573353)

    //main
    val BgColorLightOrange = Color(0xFFFFF3E9)
    val TopAppBarTextColor = DarkPurple
    val TopAppBarBackBtnBg = Color(0x1A573353)

    //home
    val habitIconColorYellow = Color(0xFFFC9D45)
    val habitIconColorRed = Color(0xFFF65B4E)
    val habitIconColorBlue = Color(0xFF29319F)
    val habitIconColorPurple = Color(0xFF973456)
    val habitCardBg = Color(0xFFFFFFFF)
    val homeQuoteTextPurple = DarkPurple
    val homeQuoteAuthor = Color(0x80573353)
    val homeDayOfWeekPurple = DarkPurple
    val homeDatePurple50 = Color(0x80573353)

    //dialog congrats
    val dialogTitleColor = DarkPurple
    val dialogSubtitleColor = homeQuoteAuthor

    //orange circle btn
    val circleBtnColor = Color(0xFFFC9D45)
}