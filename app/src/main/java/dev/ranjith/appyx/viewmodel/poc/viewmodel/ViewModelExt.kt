package dev.ranjith.appyx.viewmodel.poc.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.bumble.appyx.core.node.LocalNode

inline fun <reified VM : ViewModel> ViewModelNode.nodeViewModel(
    noinline keyFactory: (() -> String)? = null,
    factory: ViewModelProvider.Factory? = null
): VM {
    return _viewModel(this, keyFactory, factory)
}

@Suppress("ThrowsCount")
@Composable
inline fun <reified VM : ViewModel> rememberNodeViewModel(
    noinline keyFactory: (() -> String)? = null,
    factory: ViewModelProvider.Factory? = null
): VM {
    val currentNode = LocalNode.current ?: throw IllegalStateException("Not attached to any node")
    val localViewModelNode = LocalNode.current as? ViewModelNode
    return remember(currentNode) {
        val viewModelStoreOwner = localViewModelNode ?: throw IllegalStateException(
            "Node ($currentNode) is not an instance of ViewModelNode"
        )
        _viewModel(viewModelStoreOwner, keyFactory, factory)
    }
}

inline fun <reified VM : ViewModel> _viewModel(
    vmStoreOwner: ViewModelStoreOwner,
    noinline keyFactory: (() -> String)? = null,
    factory: ViewModelProvider.Factory? = null
): VM {
    val key: String = keyFactory?.invoke() ?: VM::class.qualifiedName ?: VM::class.simpleName!!
    return if (factory == null) {
        ViewModelProvider(vmStoreOwner)[VM::class.java]
    } else {
        ViewModelProvider(vmStoreOwner, factory)[key, VM::class.java]
    }
}