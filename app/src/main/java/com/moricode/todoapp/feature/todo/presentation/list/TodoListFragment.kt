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
                print(it);
                vm.adapter.submitData(it)
            }
        }
    }

    override fun onActions(action: Actions) {
        super.onActions(action)
        when (action) {
            is TodoListVM.OnCreateBtnClicked -> {
                navigate(R.id.action_todoListFragment_to_todoCreationFragment)
            }
            is TodoListVM.OnItemClicked -> {

                //navigate(R.id.action_todoListFragment_to_todoCreationFragment, action.entity)
            }
        }
    }

}