package dev.ranjith.appyx.viewmodel.poc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.bumble.appyx.core.modality.BuildContext
import dev.ranjith.appyx.viewmodel.poc.viewmodel.ViewModelNode
import dev.ranjith.appyx.viewmodel.poc.viewmodel.nodeViewModel

class FirstNode(
    buildContext: BuildContext,
    val onFinish: () -> Unit
) : ViewModelNode(buildContext) {

    private val viewModel: FirstNodeViewModel = nodeViewModel()

    @Composable
    override fun View(modifier: Modifier) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "First VM node")
            Text(text = "VM instance: ${System.identityHashCode(viewModel)}")
            Button(
                onClick = onFinish,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Go to second node")
            }
        }
    }
}

class FirstNodeViewModel() : ViewModel()