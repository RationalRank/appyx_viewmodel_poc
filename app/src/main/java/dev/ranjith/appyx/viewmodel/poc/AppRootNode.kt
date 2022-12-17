package dev.ranjith.appyx.viewmodel.poc

import android.os.Parcelable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.push
import kotlinx.parcelize.Parcelize

class AppRootNode(
    buildContext: BuildContext,
    private val backStack: BackStack<Routing> = BackStack(
        initialElement = Routing.FirstNode,
        savedStateMap = buildContext.savedStateMap
    )
) : ParentNode<AppRootNode.Routing>(backStack, buildContext) {

    sealed class Routing: Parcelable {
        @Parcelize
        object FirstNode : Routing()
        @Parcelize
        object SecondNode : Routing()
    }

    override fun resolve(navTarget: Routing, buildContext: BuildContext): Node {
        return when (navTarget) {
            Routing.FirstNode -> FirstNode(buildContext) {
                backStack.push(Routing.SecondNode)
            }

            Routing.SecondNode -> SecondNode(buildContext)
        }
    }

    @Composable
    override fun View(modifier: Modifier) {
        Children(
            modifier = modifier.fillMaxSize(),
            navModel = backStack
        )
    }
}