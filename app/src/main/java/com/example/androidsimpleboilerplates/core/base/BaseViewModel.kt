package com.example.androidsimpleboilerplates.core.base

import androidx.lifecycle.ViewModel
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    override fun onCleared() {
        Timber.d("onCleared() called")
        onClear()
        super.onCleared()
    }

    abstract fun onClear()
}