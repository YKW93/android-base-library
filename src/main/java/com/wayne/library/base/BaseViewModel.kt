package com.wayne.library.base

import androidx.lifecycle.ViewModel
import com.wayne.library.utils.LiveEvent


abstract class BaseViewModel : ViewModel() {
    val toast = LiveEvent<String>()
}