package com.wayne.library.utils

import android.view.View
import java.util.concurrent.atomic.AtomicBoolean

class OnSingleClickListener(private val listener: View.OnClickListener) : View.OnClickListener {
    private val canClick = AtomicBoolean(true)

    override fun onClick(v: View) {
        if (canClick.getAndSet(false)) {
            v.postDelayed({ canClick.set(true) },
                INTERVAL_MILLIS
            )
            listener.onClick(v)
        }
    }

    companion object {
        private const val INTERVAL_MILLIS = 1000L
    }
}