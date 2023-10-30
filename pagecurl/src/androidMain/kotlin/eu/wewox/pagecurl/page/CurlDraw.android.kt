package eu.wewox.pagecurl.page

import android.graphics.BlurMaskFilter
import androidx.compose.ui.graphics.Paint

actual fun Paint.setBlurred(value: Float) {
    if (value == 0f) return
    asFrameworkPaint().maskFilter = BlurMaskFilter(value, BlurMaskFilter.Blur.NORMAL)
}