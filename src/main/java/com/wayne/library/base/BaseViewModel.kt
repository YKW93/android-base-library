package com.wayne.library.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavArgs
import com.wayne.library.utils.EmptyNavArgs
import com.wayne.library.utils.LiveEvent
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


abstract class BaseViewModel : ViewModel() {
    val toast = LiveEvent<String>()
    val navArgsFlow = MutableStateFlow<NavArgs>(EmptyNavArgs)

    fun navArgs(navArgs: NavArgs) {
        viewModelScope.launch {
            navArgsFlow.emit(navArgs)
        }
    }

    inline fun <reified T : NavArgs> navArgs(): Flow<T> = navArgsFlow.filterIsInstance()
}