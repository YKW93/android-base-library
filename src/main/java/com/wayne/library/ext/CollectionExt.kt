package com.wayne.library.ext

inline fun <T> Collection<T>?.whenNotNullNorEmpty(func: (Collection<T>) -> Unit) {
    if (this != null && this.isNotEmpty()) {
        func(this)
    }
}