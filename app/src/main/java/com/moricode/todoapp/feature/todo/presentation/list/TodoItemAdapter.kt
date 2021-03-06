package com.moricode.todoapp.feature.todo.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.moricode.todoapp.databinding.TodoItemBinding
import com.moricode.todoapp.feature.todo.domain.TodoEntity


class TodoItemAdapter(
    val onClick: (item: TodoEntity?) -> Unit,
    val onLongClick: (item: TodoEntity?) -> Unit,
) :
    PagingDataAdapter<TodoEntity, TodoViewHolder>(Companion) {
    companion object : DiffUtil.ItemCallback<TodoEntity>() {
        override fun areItemsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = getItem(position) ?: return
        val binding = holder.binding as TodoItemBinding
        binding.todoEntity = item
        binding.root.apply {
            setOnClickListener {
                onClick(item)
            }
            setOnLongClickListener {
                onLongClick(item)
                return@setOnLongClickListener true
            }
        }
        binding.executePendingBindings()
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