package dev.ranjith.appyx.viewmodel.poc.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bumble.appyx.core.integrationpoint.NodeActivity
import com.bumble.appyx.utils.customisations.NodeCustomisationDirectoryImpl
import dev.ranjith.appyx.viewmodel.poc.viewmodel.NonConfigRetainStore

open class VmNodeActivity : NodeActivity() {

    private val nonConfigRetainStore: NonConfigRetainStore by lazy {
        ViewModelProvider(this).get()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appyxIntegrationPoint = CustomActivityIntegrationPoint(
            activity = this,
            savedInstanceState = savedInstanceState
        )
    }

    fun getDefaultNodeCustomisationDirectory(): NodeCustomisationDirectoryImpl {
        val nodeCustomisationDirectory = NodeCustomisationDirectoryImpl()
        nodeCustomisationDirectory.put(NonConfigRetainStore::class.java, nonConfigRetainStore)
        return nodeCustomisationDirectory
    }
}
