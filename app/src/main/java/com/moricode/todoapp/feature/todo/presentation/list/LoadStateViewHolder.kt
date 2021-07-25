package com.moricode.todoapp.feature.todo.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.moricode.todoapp.R
import com.moricode.todoapp.databinding.LoadStateItemBinding
import com.wang.avi.AVLoadingIndicatorView

class LoadStateViewHolder(
    parent: ViewGroup,
    retry: () -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.load_state_item, parent, false)
) {
    private val binding = LoadStateItemBinding.bind(itemView)
    private val progressBar: AVLoadingIndicatorView = binding.progressBar
    private val errorMsg: TextView = binding.errorMsg
    private val retry: ImageButton = binding.retry
        .also {
            it.setOnClickListener { retry() }
        }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            errorMsg.text = loadState.error.localizedMessage
        }

        progressBar.visibility = if (loadState is LoadState.Loading) View.VISIBLE else View.GONE
        retry.isVisible = loadState is LoadState.Error
        errorMsg.isVisible = loadState is LoadState.Error
    }
}