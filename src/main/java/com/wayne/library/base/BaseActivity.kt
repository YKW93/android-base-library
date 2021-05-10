package com.wayne.library.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelLazy
import com.wayne.library.BR
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
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
        binding.run {
            lifecycleOwner = this@BaseActivity
            setVariable(BR.vm, viewModel)
        }
    }
}