package dev.ranjith.appyx.viewmodel.poc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import com.bumble.appyx.utils.customisations.NodeCustomisation
import timber.log.Timber

class NonConfigRetainStore : ViewModel(), NodeCustomisation {

    private val map: HashMap<String, ViewModelStore> = hashMapOf()

    fun getViewModelStore(key: String): ViewModelStore {
        Timber.d("GET VM store for key - $key")
        return map.getOrPut(key) {
            ViewModelStore()
        }
    }

    fun clear(key: String) {
        Timber.d("CLEAR VM store for key - $key")
        map[key]?.clear()
    }
}
