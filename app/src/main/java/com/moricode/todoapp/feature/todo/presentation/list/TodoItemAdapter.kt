package com.moricode.todoapp.feature.todo.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.moricode.todoapp.databinding.TodoItemBinding
import com.moricode.todoapp.feature.todo.domain.TodoEntity


class TodoItemAdapter : PagingDataAdapter<TodoEntity, TodoViewHolder>(Companion) {
    companion object : DiffUtil.ItemCallback<TodoEntity>() {
        override fun areItemsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val binding = holder.binding as TodoItemBinding
        binding.todoEntity = getItem(position) ?: return
        binding.executePendingBindings()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        print("attached")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TodoItemBinding.inflate(layoutInflater, parent, false)
        return TodoViewHolder(binding)
    }
}

class TodoViewHolder(
    val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root)