package com.moricode.todoapp.feature.todo.presentation.list

import com.moricode.todoapp.R
import com.moricode.todoapp.core.base.Actions
import com.moricode.todoapp.core.base.BaseFragment
import com.moricode.todoapp.core.util.navigate
import com.moricode.todoapp.databinding.FragmentTodoListBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class TodoListFragment : BaseFragment<FragmentTodoListBinding, TodoListVM>() {

    override val vm: TodoListVM
        get() = getViewModel<TodoListVM>()

    override fun getLayoutId(): Int = R.layout.fragment_todo_list

    override fun onActions(action: Actions) {
        super.onActions(action)
        when (action) {
            is TodoListVM.OnCreateBtnClicked -> {
                navigate(R.id.action_todoListFragment_to_todoCreationFragment)
            }
        }
    }

}