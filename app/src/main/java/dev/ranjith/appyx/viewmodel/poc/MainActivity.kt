package dev.ranjith.appyx.viewmodel.poc

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.integration.NodeHost
import dev.ranjith.appyx.viewmodel.poc.activity.VmNodeActivity
import dev.ranjith.appyx.viewmodel.poc.ui.theme.AppyxviewmodelpocTheme
import timber.log.Timber

class MainActivity : VmNodeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val nodeCustomisationDirectory = getDefaultNodeCustomisationDirectory()
        setContent {
            AppyxviewmodelpocTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NodeHost(
                        integrationPoint = appyxIntegrationPoint,
                        customisations = nodeCustomisationDirectory
                    ) {
                        AppRootNode(buildContext = it)
                    }
                }
            }
        }
    }
}
