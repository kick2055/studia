package com.todolist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.todolist.TaskModel.TaskStorage
import java.io.Serializable

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_TASK_ID = "arg_task_id"

/**
 * A simple [Fragment] subclass.
 * Use the [TaskDetails.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskDetails : Fragment() {
    lateinit var taskNameEdit: EditText
    lateinit var taskDate: Button
    lateinit var taskDone: CheckBox

    private var task: TaskStorage.TaskItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val index = arguments?.getSerializable(/* key = */ ARG_TASK_ID);
        task = TaskStorage.ITEM_MAP[index]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_details, container, false)

        taskNameEdit = view.findViewById(R.id.task_name_edit)
        taskNameEdit.setText(task?.name)
        taskNameEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                task?.name = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //task?.name = s.toString()
            }
        })

        taskDate = view.findViewById(R.id.task_date)
        taskDate.text = task?.date?.toLocaleString() ?: "NULL"
        taskDate.isEnabled = false

        taskDone = view.findViewById(R.id.task_done)
        taskDone.isChecked = task?.done ?: false
        taskDone.setOnCheckedChangeListener{ _, isChecked -> task?.done = isChecked }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(taskId: String) =
            TaskDetails().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_TASK_ID, taskId as Serializable)
                }
            }
    }
}