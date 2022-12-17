package dev.ranjith.appyx.viewmodel.poc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.bumble.appyx.core.modality.BuildContext
import dev.ranjith.appyx.viewmodel.poc.viewmodel.ViewModelNode
import dev.ranjith.appyx.viewmodel.poc.viewmodel.nodeViewModel

class SecondNode(buildContext: BuildContext) : ViewModelNode(buildContext) {

    private val viewModel: SecondNodeViewModel = nodeViewModel()

    @Composable
    override fun View(modifier: Modifier) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Second VM node")
            Text(text = "VM instance: ${System.identityHashCode(viewModel)}")
        }
    }
}

class SecondNodeViewModel() : ViewModel()