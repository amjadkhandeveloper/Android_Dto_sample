package demo.amjadkhan.dtosample.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Common UI Extension Functions
 * 
 * This file contains reusable extension functions for UI components.
 * These functions can be used across the app to maintain consistency
 * and reduce code duplication.
 */

/**
 * Adds standard padding to a composable.
 * Useful for consistent spacing throughout the app.
 */
fun Modifier.standardPadding(
    start: Int = 16,
    top: Int = 16,
    end: Int = 16,
    bottom: Int = 16
): Modifier {
    return this.padding(
        start = start.dp,
        top = top.dp,
        end = end.dp,
        bottom = bottom.dp
    )
}

/**
 * Adds horizontal padding to a composable.
 */
fun Modifier.horizontalPadding(padding: Int = 16): Modifier {
    return this.padding(horizontal = padding.dp)
}

/**
 * Adds vertical padding to a composable.
 */
fun Modifier.verticalPadding(padding: Int = 16): Modifier {
    return this.padding(vertical = padding.dp)
}

/**
 * Adds clickable behavior without ripple effect.
 * Useful for custom clickable areas.
 */
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier {
    return this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}

/**
 * Creates standard padding values for screens.
 */
fun standardScreenPadding(): PaddingValues {
    return PaddingValues(
        start = 16.dp,
        top = 16.dp,
        end = 16.dp,
        bottom = 16.dp
    )
}

/**
 * Creates horizontal padding values.
 */
fun horizontalScreenPadding(padding: Int = 16): PaddingValues {
    return PaddingValues(horizontal = padding.dp)
}

/**
 * Creates vertical padding values.
 */
fun verticalScreenPadding(padding: Int = 16): PaddingValues {
    return PaddingValues(vertical = padding.dp)
}

// Future extension functions examples:
// 
// fun String.toSpannable(): SpannableString { ... }
// fun Modifier.shimmerEffect(): Modifier { ... }
// fun Modifier.shadow(elevation: Int): Modifier { ... }
// fun Modifier.roundedCorners(radius: Int): Modifier { ... }
// fun View.show() { visibility = View.VISIBLE }
// fun View.hide() { visibility = View.GONE }
// fun Context.showToast(message: String) { ... }
// fun String.isValidEmail(): Boolean { ... }
// fun String.capitalizeWords(): String { ... }