package eu.wewox.pagecurl

import androidx.compose.animation.Crossfade
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import eu.wewox.pagecurl.screens.BackPagePageCurlScreen
import eu.wewox.pagecurl.screens.InteractionConfigInPageCurlScreen
import eu.wewox.pagecurl.screens.PagingPageCurlScreen
import eu.wewox.pagecurl.screens.SettingsPageCurlScreen
import eu.wewox.pagecurl.screens.ShadowInPageCurlScreen
import eu.wewox.pagecurl.screens.SimplePageCurlScreen
import eu.wewox.pagecurl.screens.StateInPageCurlScreen
import eu.wewox.pagecurl.ui.theme.PageCurlTheme

/**
 * Main view for demo application.
 * Contains simple "Crossfade" based navigation to various examples.
 */
@Composable
fun App(modifier: Modifier = Modifier) {
    PageCurlTheme {
        var example by rememberSaveable { mutableStateOf<Example?>(null) }
        PageStateHandler.callNavToMain = { example = null }
        Surface(color = MaterialTheme.colorScheme.background) {
            Crossfade(targetState = example, modifier, label = "Crossfade") { selected ->
                when (selected) {
                    null -> RootScreen(onExampleClick = { PageStateHandler.onLeftMain?.invoke(); example = it })
                    Example.SimplePageCurl -> BackablePageScreen { SimplePageCurlScreen() }
                    Example.PagingPageCurl -> BackablePageScreen { PagingPageCurlScreen() }
                    Example.SettingsPageCurl -> BackablePageScreen { SettingsPageCurlScreen() }
                    Example.StateInPageCurl -> BackablePageScreen { StateInPageCurlScreen() }
                    Example.InteractionConfigInPageCurl -> BackablePageScreen { InteractionConfigInPageCurlScreen() }
                    Example.ShadowPageCurl -> BackablePageScreen { ShadowInPageCurlScreen() }
                    Example.BackPagePageCurl -> BackablePageScreen { BackPagePageCurlScreen() }
                }
            }
        }
    }
}

object PageStateHandler {

    internal var onLeftMain: (() -> Unit)? = null
    internal var callNavToMain: (() -> Unit)? = null

    fun onLeftMain(onLeftMain: () -> Unit) {
        this.onLeftMain = onLeftMain
    }

    fun navToMain() {
        callNavToMain?.invoke()
    }
}

@Composable
internal expect fun BackablePageScreen(content: @Composable () -> Unit)