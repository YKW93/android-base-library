package com.wayne.library.ext

import android.widget.TextView
import androidx.core.text.parseAsHtml
import androidx.databinding.BindingAdapter

@BindingAdapter("htmlText")
fun TextView.bindHtmlText(value: String?) {
    text = value?.parseAsHtml() ?: ""
}