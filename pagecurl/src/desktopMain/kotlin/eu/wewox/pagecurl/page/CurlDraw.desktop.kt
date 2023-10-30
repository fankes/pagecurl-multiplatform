package eu.wewox.pagecurl.page

import androidx.compose.ui.graphics.Paint
import org.jetbrains.skia.FilterBlurMode
import org.jetbrains.skia.MaskFilter

actual fun Paint.setBlurred(value: Float) {
    if (value == 0f) return
    asFrameworkPaint().maskFilter = MaskFilter.makeBlur(FilterBlurMode.NORMAL, value / 2f)
}