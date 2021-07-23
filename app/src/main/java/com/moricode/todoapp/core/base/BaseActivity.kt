package com.moricode.todoapp.core.base


import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import timber.log.Timber
import kotlin.coroutines.CoroutineContext
import com.moricode.todoapp.BR


abstract class BaseActivity<T : ViewDataBinding, out V : BaseViewModel> : AppCompatActivity(),
    CoroutineScope {

    abstract val vm: V
    private var sharedVm: BaseViewModel? = null
    protected open fun getBindingVariable(): Int = BR.vm
    lateinit var viewDataBinding: T

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    @IdRes
    open fun getContainerId(): Int = 0

    private val job = SupervisorJob()


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        performDataBinding()
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.setVariable(getBindingVariable(), vm)
        viewDataBinding.executePendingBindings()
    }


    open fun onActions(action: Actions) {
    }

    override fun onStop() {
        super.onStop()
        vm.actions.removeObservers(this)
        sharedVm?.actions?.removeObservers(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancelChildren()
    }

    fun getCurrentFragment(): BaseFragment<*, *>? {
        val navHostFragment = supportFragmentManager.findFragmentById(getContainerId())
        return navHostFragment?.childFragmentManager?.fragments?.firstOrNull() as? BaseFragment<*, *>
    }

}


