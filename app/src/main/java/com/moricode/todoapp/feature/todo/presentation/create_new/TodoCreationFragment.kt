package com.moricode.todoapp.feature.todo.presentation.create_new

import com.moricode.todoapp.R
import com.moricode.todoapp.core.base.BaseFragment
import com.moricode.todoapp.databinding.FragmentTodoListBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel


class TodoCreationFragment : BaseFragment<FragmentTodoListBinding, TodoCreationVM>() {

    override val vm: TodoCreationVM
        get() = getViewModel()

    override fun getLayoutId(): Int = R.layout.fragment_todo_creation
}