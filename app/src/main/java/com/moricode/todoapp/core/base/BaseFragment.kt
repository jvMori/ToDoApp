package com.moricode.todoapp.core.base


import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.moricode.todoapp.BR
import timber.log.Timber


abstract class BaseFragment<T : ViewDataBinding, out V : BaseViewModel> : Fragment() {

    abstract val vm: V
    protected open fun getBindingVariable(): Int = BR.vm

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    private lateinit var viewDataBinding: T
    open fun refresh(): Boolean {
        return false
    }

    internal fun getBinding() = viewDataBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        return viewDataBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
    }


    override fun onStart() {
        super.onStart()
        vm.actions.observe(this) {
            Timber.d("Received ${this.javaClass.simpleName}")
            onActions(it)
        }
    }


    private fun bindView() {
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.setVariable(getBindingVariable(), vm)
        viewDataBinding.executePendingBindings()
    }


    override fun onStop() {
        super.onStop()
        vm.actions.removeObservers(this)
    }

    open fun onActions(action: Actions) {
        parent<BaseActivity<*, *>>()?.onActions(action)
    }

    fun refreshBindings() {
        viewDataBinding.invalidateAll()
    }

    inline fun <reified T : BaseActivity<*, *>> parent(): T? = activity as? T
    inline fun <reified T : BaseFragment<*, *>> parentFragment(): T? = parentFragment as? T

}
