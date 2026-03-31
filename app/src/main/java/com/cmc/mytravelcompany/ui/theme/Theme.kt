package com.example.compose
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.cmc.mytravelcompany.ui.theme.backgroundDark
import com.cmc.mytravelcompany.ui.theme.backgroundDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.backgroundDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.backgroundLight
import com.cmc.mytravelcompany.ui.theme.backgroundLightHighContrast
import com.cmc.mytravelcompany.ui.theme.backgroundLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.errorContainerDark
import com.cmc.mytravelcompany.ui.theme.errorContainerDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.errorContainerDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.errorContainerLight
import com.cmc.mytravelcompany.ui.theme.errorContainerLightHighContrast
import com.cmc.mytravelcompany.ui.theme.errorContainerLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.errorDark
import com.cmc.mytravelcompany.ui.theme.errorDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.errorDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.errorLight
import com.cmc.mytravelcompany.ui.theme.errorLightHighContrast
import com.cmc.mytravelcompany.ui.theme.errorLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.inverseOnSurfaceDark
import com.cmc.mytravelcompany.ui.theme.inverseOnSurfaceDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.inverseOnSurfaceLight
import com.cmc.mytravelcompany.ui.theme.inverseOnSurfaceLightHighContrast
import com.cmc.mytravelcompany.ui.theme.inverseOnSurfaceLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.inversePrimaryDark
import com.cmc.mytravelcompany.ui.theme.inversePrimaryDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.inversePrimaryDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.inversePrimaryLight
import com.cmc.mytravelcompany.ui.theme.inversePrimaryLightHighContrast
import com.cmc.mytravelcompany.ui.theme.inversePrimaryLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.inverseSurfaceDark
import com.cmc.mytravelcompany.ui.theme.inverseSurfaceDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.inverseSurfaceDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.inverseSurfaceLight
import com.cmc.mytravelcompany.ui.theme.inverseSurfaceLightHighContrast
import com.cmc.mytravelcompany.ui.theme.inverseSurfaceLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.onBackgroundDark
import com.cmc.mytravelcompany.ui.theme.onBackgroundDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.onBackgroundDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.onBackgroundLight
import com.cmc.mytravelcompany.ui.theme.onBackgroundLightHighContrast
import com.cmc.mytravelcompany.ui.theme.onBackgroundLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.onErrorContainerDark
import com.cmc.mytravelcompany.ui.theme.onErrorContainerDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.onErrorContainerDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.onErrorContainerLight
import com.cmc.mytravelcompany.ui.theme.onErrorContainerLightHighContrast
import com.cmc.mytravelcompany.ui.theme.onErrorContainerLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.onErrorDark
import com.cmc.mytravelcompany.ui.theme.onErrorDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.onErrorDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.onErrorLight
import com.cmc.mytravelcompany.ui.theme.onErrorLightHighContrast
import com.cmc.mytravelcompany.ui.theme.onErrorLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.onPrimaryContainerDark
import com.cmc.mytravelcompany.ui.theme.onPrimaryContainerDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.onPrimaryContainerDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.onPrimaryContainerLight
import com.cmc.mytravelcompany.ui.theme.onPrimaryContainerLightHighContrast
import com.cmc.mytravelcompany.ui.theme.onPrimaryContainerLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.onPrimaryDark
import com.cmc.mytravelcompany.ui.theme.onPrimaryDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.onPrimaryDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.onPrimaryLight
import com.cmc.mytravelcompany.ui.theme.onPrimaryLightHighContrast
import com.cmc.mytravelcompany.ui.theme.onPrimaryLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.onSecondaryContainerDark
import com.cmc.mytravelcompany.ui.theme.onSecondaryContainerDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.onSecondaryContainerDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.onSecondaryContainerLight
import com.cmc.mytravelcompany.ui.theme.onSecondaryContainerLightHighContrast
import com.cmc.mytravelcompany.ui.theme.onSecondaryContainerLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.onSecondaryDark
import com.cmc.mytravelcompany.ui.theme.onSecondaryDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.onSecondaryDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.onSecondaryLight
import com.cmc.mytravelcompany.ui.theme.onSecondaryLightHighContrast
import com.cmc.mytravelcompany.ui.theme.onSecondaryLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.onSurfaceDark
import com.cmc.mytravelcompany.ui.theme.onSurfaceDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.onSurfaceDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.onSurfaceLight
import com.cmc.mytravelcompany.ui.theme.onSurfaceLightHighContrast
import com.cmc.mytravelcompany.ui.theme.onSurfaceLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.onSurfaceVariantDark
import com.cmc.mytravelcompany.ui.theme.onSurfaceVariantDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.onSurfaceVariantDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.onSurfaceVariantLight
import com.cmc.mytravelcompany.ui.theme.onSurfaceVariantLightHighContrast
import com.cmc.mytravelcompany.ui.theme.onSurfaceVariantLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.onTertiaryContainerDark
import com.cmc.mytravelcompany.ui.theme.onTertiaryContainerDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.onTertiaryContainerDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.onTertiaryContainerLight
import com.cmc.mytravelcompany.ui.theme.onTertiaryContainerLightHighContrast
import com.cmc.mytravelcompany.ui.theme.onTertiaryContainerLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.onTertiaryDark
import com.cmc.mytravelcompany.ui.theme.onTertiaryDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.onTertiaryDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.onTertiaryLight
import com.cmc.mytravelcompany.ui.theme.onTertiaryLightHighContrast
import com.cmc.mytravelcompany.ui.theme.onTertiaryLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.outlineDark
import com.cmc.mytravelcompany.ui.theme.outlineDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.outlineDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.outlineLight
import com.cmc.mytravelcompany.ui.theme.outlineLightHighContrast
import com.cmc.mytravelcompany.ui.theme.outlineLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.outlineVariantDark
import com.cmc.mytravelcompany.ui.theme.outlineVariantDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.outlineVariantDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.outlineVariantLight
import com.cmc.mytravelcompany.ui.theme.outlineVariantLightHighContrast
import com.cmc.mytravelcompany.ui.theme.outlineVariantLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.primaryContainerDark
import com.cmc.mytravelcompany.ui.theme.primaryContainerDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.primaryContainerDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.primaryContainerLight
import com.cmc.mytravelcompany.ui.theme.primaryContainerLightHighContrast
import com.cmc.mytravelcompany.ui.theme.primaryContainerLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.primaryDark
import com.cmc.mytravelcompany.ui.theme.primaryDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.primaryDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.primaryLight
import com.cmc.mytravelcompany.ui.theme.primaryLightHighContrast
import com.cmc.mytravelcompany.ui.theme.primaryLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.scrimDark
import com.cmc.mytravelcompany.ui.theme.scrimDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.scrimDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.scrimLight
import com.cmc.mytravelcompany.ui.theme.scrimLightHighContrast
import com.cmc.mytravelcompany.ui.theme.scrimLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.secondaryContainerDark
import com.cmc.mytravelcompany.ui.theme.secondaryContainerDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.secondaryContainerDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.secondaryContainerLight
import com.cmc.mytravelcompany.ui.theme.secondaryContainerLightHighContrast
import com.cmc.mytravelcompany.ui.theme.secondaryContainerLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.secondaryDark
import com.cmc.mytravelcompany.ui.theme.secondaryDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.secondaryDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.secondaryLight
import com.cmc.mytravelcompany.ui.theme.secondaryLightHighContrast
import com.cmc.mytravelcompany.ui.theme.secondaryLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.surfaceBrightDark
import com.cmc.mytravelcompany.ui.theme.surfaceBrightDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.surfaceBrightDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.surfaceBrightLight
import com.cmc.mytravelcompany.ui.theme.surfaceBrightLightHighContrast
import com.cmc.mytravelcompany.ui.theme.surfaceBrightLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.surfaceContainerDark
import com.cmc.mytravelcompany.ui.theme.surfaceContainerDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.surfaceContainerDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.surfaceContainerHighDark
import com.cmc.mytravelcompany.ui.theme.surfaceContainerHighDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.surfaceContainerHighDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.surfaceContainerHighLight
import com.cmc.mytravelcompany.ui.theme.surfaceContainerHighLightHighContrast
import com.cmc.mytravelcompany.ui.theme.surfaceContainerHighLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.surfaceContainerHighestDark
import com.cmc.mytravelcompany.ui.theme.surfaceContainerHighestDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.surfaceContainerHighestLight
import com.cmc.mytravelcompany.ui.theme.surfaceContainerHighestLightHighContrast
import com.cmc.mytravelcompany.ui.theme.surfaceContainerHighestLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.surfaceContainerLight
import com.cmc.mytravelcompany.ui.theme.surfaceContainerLightHighContrast
import com.cmc.mytravelcompany.ui.theme.surfaceContainerLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.surfaceContainerLowDark
import com.cmc.mytravelcompany.ui.theme.surfaceContainerLowDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.surfaceContainerLowDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.surfaceContainerLowLight
import com.cmc.mytravelcompany.ui.theme.surfaceContainerLowLightHighContrast
import com.cmc.mytravelcompany.ui.theme.surfaceContainerLowLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.surfaceContainerLowestDark
import com.cmc.mytravelcompany.ui.theme.surfaceContainerLowestDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.surfaceContainerLowestLight
import com.cmc.mytravelcompany.ui.theme.surfaceContainerLowestLightHighContrast
import com.cmc.mytravelcompany.ui.theme.surfaceContainerLowestLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.surfaceDark
import com.cmc.mytravelcompany.ui.theme.surfaceDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.surfaceDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.surfaceDimDark
import com.cmc.mytravelcompany.ui.theme.surfaceDimDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.surfaceDimDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.surfaceDimLight
import com.cmc.mytravelcompany.ui.theme.surfaceDimLightHighContrast
import com.cmc.mytravelcompany.ui.theme.surfaceDimLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.surfaceLight
import com.cmc.mytravelcompany.ui.theme.surfaceLightHighContrast
import com.cmc.mytravelcompany.ui.theme.surfaceLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.surfaceVariantDark
import com.cmc.mytravelcompany.ui.theme.surfaceVariantDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.surfaceVariantDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.surfaceVariantLight
import com.cmc.mytravelcompany.ui.theme.surfaceVariantLightHighContrast
import com.cmc.mytravelcompany.ui.theme.surfaceVariantLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.tertiaryContainerDark
import com.cmc.mytravelcompany.ui.theme.tertiaryContainerDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.tertiaryContainerDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.tertiaryContainerLight
import com.cmc.mytravelcompany.ui.theme.tertiaryContainerLightHighContrast
import com.cmc.mytravelcompany.ui.theme.tertiaryContainerLightMediumContrast
import com.cmc.mytravelcompany.ui.theme.tertiaryDark
import com.cmc.mytravelcompany.ui.theme.tertiaryDarkHighContrast
import com.cmc.mytravelcompany.ui.theme.tertiaryDarkMediumContrast
import com.cmc.mytravelcompany.ui.theme.tertiaryLight
import com.cmc.mytravelcompany.ui.theme.tertiaryLightHighContrast
import com.cmc.mytravelcompany.ui.theme.tertiaryLightMediumContrast
import com.example.ui.theme.AppTypography

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun InstaDevTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> darkScheme
      else -> lightScheme
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography,
    content = content
  )
}

