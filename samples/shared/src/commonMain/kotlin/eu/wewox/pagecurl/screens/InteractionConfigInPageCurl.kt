package eu.wewox.pagecurl.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toOffset
import eu.wewox.pagecurl.HowToPageData
import eu.wewox.pagecurl.components.HowToPage
import eu.wewox.pagecurl.components.ZoomOutLayout
import eu.wewox.pagecurl.config.PageCurlConfig
import eu.wewox.pagecurl.config.rememberPageCurlConfig
import eu.wewox.pagecurl.page.PageCurl
import eu.wewox.pagecurl.page.rememberPageCurlState
import eu.wewox.pagecurl.ui.SpacingLarge
import eu.wewox.pagecurl.ui.SpacingMedium
import eu.wewox.pagecurl.ui.SpacingSmall

/**
 * Interactions Configurations In Page Curl.
 * Example interactions (drag / tap) can be customized.
 */
@Composable
fun InteractionConfigInPageCurlScreen() {
    Box(Modifier.fillMaxSize()) {
        val pages = remember { HowToPageData.interactionSettingsHowToPages }
        var zoomOut by remember { mutableStateOf(false) }
        val state = rememberPageCurlState()
        val config = rememberPageCurlConfig(
            onCustomTap = { size, position ->
                // When PageCurl is zoomed out then zoom back in
                // Else detect tap somewhere in the center with 64 radius and zoom out a PageCurl
                if (zoomOut) {
                    zoomOut = false
                    true
                } else if ((position - size.center.toOffset()).getDistance() < 64.dp.toPx()) {
                    zoomOut = true
                    true
                } else {
                    false
                }
            }
        )

        ZoomOutLayout(
            zoomOut = zoomOut,
            config = config,
            bottom = { SettingsRow(config) },
        ) {
            PageCurl(
                count = pages.size,
                state = state,
                config = config,
            ) { index ->
                HowToPage(index, pages[index])
            }
        }
    }
}

@Composable
private fun SettingsRow(
    config: PageCurlConfig,
    modifier: Modifier = Modifier
) {
    var selectedOption by remember { mutableStateOf(InteractionOption.Drag) }
    Column(
        verticalArrangement = Arrangement.spacedBy(SpacingSmall),
        modifier = modifier
            .fillMaxWidth()
            .padding(SpacingLarge)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .selectableGroup()
        ) {
            InteractionOption.values().forEach { option ->
                Row(
                    Modifier
                        .selectable(
                            selected = selectedOption == option,
                            onClick = { selectedOption = option },
                            role = Role.RadioButton
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedOption == option,
                        onClick = null
                    )
                    Text(
                        text = option.name,
                        modifier = Modifier.padding(start = SpacingMedium)
                    )
                }
            }
        }

        SettingsRowSlider(
            selectedOption = selectedOption,
            config = config,
        )
    }
}

@Composable
private fun SettingsRowSlider(
    selectedOption: InteractionOption,
    config: PageCurlConfig,
) {
    when (selectedOption) {
        InteractionOption.Drag -> {
            Slider(
                value = config.dragForwardInteraction.start.left,
                onValueChange = {
                    config.dragForwardInteraction = PageCurlConfig.DragInteraction(
                        Rect(it, 0.0f, 1.0f, 1.0f),
                        Rect(0.0f, 0.0f, it, 1.0f)
                    )
                    config.dragBackwardInteraction = PageCurlConfig.DragInteraction(
                        Rect(0.0f, 0.0f, it, 1.0f),
                        Rect(it, 0.0f, 1.0f, 1.0f),
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
        InteractionOption.Tap -> {
            Slider(
                value = config.tapForwardInteraction.target.left,
                onValueChange = {
                    config.tapForwardInteraction = PageCurlConfig.TapInteraction(
                        Rect(it, 0.0f, 1.0f, 1.0f),
                    )
                    config.tapBackwardInteraction = PageCurlConfig.TapInteraction(
                        Rect(0.0f, 0.0f, it, 1.0f),
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

private enum class InteractionOption { Drag, Tap }
