package com.mohaberabi.pastinandroid.model

data class UserDataModel(
    val bookmarks: Set<String> = emptySet(),
    val intersts: Set<String> = emptySet(),
    val useDynamicTheme: Boolean = false,
    val darkThemePrefs: ThemeMode = ThemeMode.DEFAULT,
    val themePrefs: ThemeType = ThemeType.ANDROID,
)

enum class ThemeMode {
    DEFAULT,
    LIGHT,
    DARK,

}

fun String.toThemeType(): ThemeType = when (this) {
    ThemeType.DEFAULT.name -> ThemeType.DEFAULT
    ThemeType.ANDROID.name -> ThemeType.ANDROID
    else -> ThemeType.DEFAULT

}

fun String.toThemeMode(): ThemeMode = when (this) {
    ThemeMode.DEFAULT.name -> ThemeMode.DEFAULT
    ThemeMode.LIGHT.name -> ThemeMode.LIGHT
    ThemeMode.DARK.name -> ThemeMode.DARK
    else -> ThemeMode.DEFAULT
}

enum class ThemeType {
    DEFAULT, ANDROID,
}

