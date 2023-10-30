package eu.wewox.pagecurl

import androidx.compose.runtime.Composable

@Composable
internal actual fun BackablePageScreen(content: @Composable () -> Unit) {
    content()
}