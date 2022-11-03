package com.todolist

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity

import com.todolist.TaskModel.TaskStorage.TaskItem
import com.todolist.databinding.FragmentTaskBinding

const val KEY_EXTRA_TASK_ID = "key_extra_task_id"

/**
 * [RecyclerView.Adapter] that can display a [TaskItem].
 */
class TaskRecyclerViewAdapter(
    private val context: Context,
    private val values: List<TaskItem>
    ) : RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @Suppress("DEPRECATION")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val taskItem = values[position]
        holder.nameTextView.text = taskItem.name
        holder.dateTextView.text = taskItem.date.toLocaleString()
        holder.taskItem = taskItem
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentTaskBinding)
            : RecyclerView.ViewHolder(binding.root), OnClickListener {
        val nameTextView: TextView = binding.textViewTaskName
        val dateTextView: TextView = binding.textViewDate
        var taskItem: TaskItem? = null

        init { itemView.setOnClickListener(this) }

        override fun toString(): String {
            return "${super.toString()} '${nameTextView.text}'${dateTextView.text}"
        }

        override fun onClick(v: View?) {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(KEY_EXTRA_TASK_ID, taskItem?.id)
            context.startActivity(intent)
        }
    }

}