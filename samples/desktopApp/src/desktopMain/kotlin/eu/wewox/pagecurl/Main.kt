package eu.wewox.pagecurl

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "PageCurl Demo",
        state = rememberWindowState(width = 450.dp, height = 750.dp)
    ) { App() }
}