package com.wayne.library.ext

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.TextViewBindingAdapter

@BindingAdapter(
    value = ["beforeTextChanged", "onTextChanged", "onAfterTextChanged"],
    requireAll = false
)
fun EditText.setTextWatcher(
    beforeTextChanged: TextViewBindingAdapter.BeforeTextChanged?,
    onTextChanged: TextViewBindingAdapter.OnTextChanged?,
    afterTextChanged: TextViewBindingAdapter.AfterTextChanged?
) {
    addTextChangedListener(beforeTextChanged = { charSequence: CharSequence?, start: Int, count: Int, after: Int ->
        beforeTextChanged?.beforeTextChanged(charSequence, start, count, after)
    }, onTextChanged = { charSequence: CharSequence?, start: Int, before: Int, count: Int ->
        onTextChanged?.onTextChanged(charSequence, start, before, count)
    }, afterTextChanged = { text ->
        afterTextChanged?.afterTextChanged(text)
    })
}