package com.wayne.library.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelLazy
import androidx.navigation.NavArgs
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.wayne.library.BR
import com.wayne.library.ext.observe
import com.wayne.library.ext.showToastMessage
import com.wayne.library.utils.BackDirections
import com.wayne.library.utils.EmptyNavArgs
import kotlin.reflect.KClass

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int,
    private val viewModelClass: KClass<VM>,
) : Fragment() {

    protected lateinit var binding: B

    protected val viewModel by ViewModelLazy(
        this.viewModelClass,
        { viewModelStore },
        { defaultViewModelProviderFactory }
    )

    protected open val navArgs: NavArgs = EmptyNavArgs

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        binding.lifecycleOwner = this
        binding.setVariable(BR.vm, viewModel)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.toast) { context?.showToastMessage(it) }
        observe(viewModel.navDirections) {
            navigation(it)
        }

        viewModel.navArgs(navArgs)
    }

    private fun navigation(navDirections: NavDirections) {
        if (navDirections is BackDirections) {
            if (navDirections.destinationId == -1) {
                findNavController().popBackStack()
            } else {
                findNavController().popBackStack(
                    navDirections.destinationId,
                    navDirections.inclusive
                )
            }
        } else {
            findNavController().navigate(navDirections)
        }
    }
}