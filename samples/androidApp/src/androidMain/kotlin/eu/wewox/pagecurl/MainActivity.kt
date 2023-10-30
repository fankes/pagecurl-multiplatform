package eu.wewox.pagecurl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent { App(Modifier.safeDrawingPadding()) }
        val callback = onBackPressedDispatcher.addCallback(enabled = false) {
            isEnabled = false
            PageStateHandler.navToMain()
        }
        PageStateHandler.onLeftMain { callback.isEnabled = true }
    }
}