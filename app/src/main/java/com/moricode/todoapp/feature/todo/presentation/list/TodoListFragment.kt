package com.moricode.todoapp.feature.todo.presentation.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.moricode.todoapp.R
import com.moricode.todoapp.core.base.Actions
import com.moricode.todoapp.core.base.BaseFragment
import com.moricode.todoapp.core.util.navigate
import com.moricode.todoapp.databinding.FragmentTodoListBinding
import com.moricode.todoapp.feature.todo.domain.TodoEntity
import com.moricode.todoapp.feature.todo.presentation.delete.DeleteAlertDialog
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

class TodoListFragment : BaseFragment<FragmentTodoListBinding, TodoListVM>() {

    override val vm: TodoListVM
        get() = getViewModel<TodoListVM>()

    override fun getLayoutId(): Int = R.layout.fragment_todo_list


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            vm.flow.collectLatest {
                vm.adapter.submitData(it)
            }
        }
        vm.handleLoadingStates()
    }

    override fun onActions(action: Actions) {
        super.onActions(action)
        when (action) {
            is TodoListVM.OnCreateBtnClicked -> {
                navigate(R.id.action_todoListFragment_to_todoCreationFragment)
            }
            is TodoListVM.OnItemClicked -> {
                TodoListFragmentDirections.actionTodoListFragmentToTodoCreationFragment(action.entity)
                    .apply {
                        navigate(this)
                    }
            }
            is TodoListVM.OnLongClick -> {
                DeleteAlertDialog.show(this.requireContext()) { confirmed ->
                    if (confirmed) vm.deleteTodo(action.entity)
                }
            }
        }
    }

}