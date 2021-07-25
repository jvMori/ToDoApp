package com.moricode.todoapp.feature.todo.presentation.create_new

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.moricode.todoapp.R
import com.moricode.todoapp.core.base.Actions
import com.moricode.todoapp.core.base.BaseFragment
import com.moricode.todoapp.core.util.Bindings
import com.moricode.todoapp.core.util.makeToast
import com.moricode.todoapp.core.util.navigate
import com.moricode.todoapp.databinding.FragmentTodoCreationBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel


class TodoCreationFragment : BaseFragment<FragmentTodoCreationBinding, TodoCreationVM>() {

    override val vm: TodoCreationVM
        get() = getViewModel()

    val args: TodoCreationFragmentArgs by navArgs()

    override fun getLayoutId(): Int = R.layout.fragment_todo_creation

    override fun onActions(action: Actions) {
        super.onActions(action)
        when (action) {
            is TodoCreationVM.Validate -> {
                Bindings.validate(getBinding().title, vm.titleMaxChar)
                Bindings.validate(getBinding().description, vm.descriptionMaxChar)
            }
            is Actions.Success<*> -> {
                makeToast("Todo successfully created!")
                navigate(R.id.action_todoCreationFragment_to_todoListFragment)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.init(args.todoEntity)
    }

}