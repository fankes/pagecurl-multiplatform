package eu.wewox.pagecurl.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toOffset
import eu.wewox.pagecurl.HowToPageData
import eu.wewox.pagecurl.components.HowToPage
import eu.wewox.pagecurl.components.SettingsPopup
import eu.wewox.pagecurl.config.rememberPageCurlConfig
import eu.wewox.pagecurl.page.PageCurl
import eu.wewox.pagecurl.page.rememberPageCurlState

/**
 * Page Curl With Settings.
 * Showcases how individual interactions can be toggled on / off.
 */
@Composable
fun SettingsPageCurlScreen() {
    Box(Modifier.fillMaxSize()) {
        val pages = remember { HowToPageData.interactionHowToPages }

        var showPopup by rememberSaveable { mutableStateOf(false) }

        val state = rememberPageCurlState()
        val config = rememberPageCurlConfig(
            onCustomTap = { size, position ->
                // Detect tap somewhere in the center with 64 radius and show popup
                if ((position - size.center.toOffset()).getDistance() < 64.dp.toPx()) {
                    showPopup = true
                    true
                } else {
                    false
                }
            }
        )

        PageCurl(
            count = pages.size,
            state = state,
            config = config,
        ) { index ->
            HowToPage(index, pages[index])
        }

        if (showPopup) {
            SettingsPopup(
                config = config,
                onDismiss = {
                    showPopup = false
                }
            )
        }
    }
}
