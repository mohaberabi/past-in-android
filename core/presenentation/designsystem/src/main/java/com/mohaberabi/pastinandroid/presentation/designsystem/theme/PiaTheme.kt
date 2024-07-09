import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.mohaberabi.pastinandroid.model.ThemeMode
import com.mohaberabi.pastinandroid.model.ThemeType
import com.mohaberabi.pastinandroid.presentation.designsystem.theme.PiaDarkColorScheme
import com.mohaberabi.pastinandroid.presentation.designsystem.theme.PiaLightColorScheme
import com.mohaberabi.pastinandroid.presentation.designsystem.theme.PiaTextTheme


@Composable
fun PiaTheme(
    dynamicColor: Boolean = true,
    theme: ThemeType = ThemeType.ANDROID,
    darkModePrefs: ThemeMode = ThemeMode.DARK,
    content: @Composable () -> Unit,
) {

    val isDark = isDarkTheme(
        themeMode = darkModePrefs,
        isSystemDark = isSystemInDarkTheme()
    )

    val colors = when (theme) {
        ThemeType.DEFAULT -> getDynamicColorScheme(
            isDark = isDark,
            isDynamic = dynamicColor,
            context = LocalContext.current
        )

        ThemeType.ANDROID -> getDefaultColorScheme(isDark)
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colors,
        typography = PiaTextTheme(isDark = isDark).typography,
        content = content
    )
}

fun getDefaultColorScheme(isDark: Boolean) =
    if (isDark) PiaDarkColorScheme
    else PiaLightColorScheme

fun getDynamicColorScheme(
    isDark: Boolean,
    isDynamic: Boolean,
    context: Context
): ColorScheme {
    val defaultColor = getDefaultColorScheme(isDark)
    val canUseDynamic = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    return if (canUseDynamic && isDynamic) {
        if (isDark) dynamicDarkColorScheme(context)
        else dynamicLightColorScheme(context)
    } else {
        defaultColor
    }
}

fun isDarkTheme(themeMode: ThemeMode, isSystemDark: Boolean): Boolean {
    return when (themeMode) {
        ThemeMode.DEFAULT -> isSystemDark
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
    }
}