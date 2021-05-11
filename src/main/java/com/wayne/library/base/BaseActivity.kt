package com.wayne.library.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelLazy
import com.wayne.library.BR
import com.wayne.library.ext.observe
import com.wayne.library.ext.showToastMessage
import kotlin.reflect.KClass

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int,
    private val viewModelClass: KClass<VM>,
) : AppCompatActivity() {

    protected lateinit var binding: B

    protected val viewModel by ViewModelLazy(
        this.viewModelClass,
        { viewModelStore },
        { defaultViewModelProviderFactory }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)

        binding.lifecycleOwner = this
        binding.setVariable(BR.vm, viewModel)

        observe(viewModel.toast) { showToastMessage(it) }
    }
}