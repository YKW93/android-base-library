package com.wayne.library.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, action: (T) -> Unit) =
    liveData.observe(this) { action(it) }

fun <T : Any, L : LiveData<T>> Fragment.observe(liveData: L, action: (T) -> Unit) =
    liveData.observe(viewLifecycleOwner) { action(it) }