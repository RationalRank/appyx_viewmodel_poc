package dev.ranjith.appyx.viewmodel.poc.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import dev.ranjith.appyx.viewmodel.poc.activity.CustomActivityIntegrationPoint

abstract class ViewModelNode(
    buildContext: BuildContext,
    private val composable: (@Composable (Modifier) -> Unit)? = null
) : Node(buildContext), ViewModelStoreOwner {

    constructor(buildContext: BuildContext) : this(buildContext, null)

    private val nonConfigRetainStore =
        buildContext.customisations.get(NonConfigRetainStore::class) ?: throw IllegalStateException(
            "NonConfigRetainStore can't be fetched from customisations. Please provide an instance of " +
                "NonConfigRetainStore via NodeHost"
        )

    init {
        lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)
                lifecycle.removeObserver(this)
                disposeIfNotChangingConfiguration()
            }
        })
    }

    override fun getViewModelStore(): ViewModelStore {
        return nonConfigRetainStore.getViewModelStore(id)
    }

    private fun disposeIfNotChangingConfiguration() {
        val activityIntegrationPoint = (integrationPoint as? CustomActivityIntegrationPoint)
            ?: throw IllegalStateException(
                "ActivityIntegrationPoint should be an instance of CustomActivityIntegrationPoint"
            )
        val activity = activityIntegrationPoint.activity
        if (!activity.isChangingConfigurations) {
            nonConfigRetainStore.clear(id)
        }
    }

    @Composable
    override fun View(modifier: Modifier) {
        composable?.invoke(modifier)
    }
}
