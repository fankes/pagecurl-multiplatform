package eu.wewox.pagecurl

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import eu.wewox.pagecurl.components.TopBar

@Composable
internal actual fun BackablePageScreen(content: @Composable () -> Unit) {
    Box {
        content()
        TopBar(title = "", onBackClick = { PageStateHandler.navToMain() })
    }
}