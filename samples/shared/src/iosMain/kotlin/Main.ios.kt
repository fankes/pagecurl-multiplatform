@file:Suppress("unused")

import androidx.compose.ui.window.ComposeUIViewController
import eu.wewox.pagecurl.App

fun createUIViewController() = ComposeUIViewController { App() }