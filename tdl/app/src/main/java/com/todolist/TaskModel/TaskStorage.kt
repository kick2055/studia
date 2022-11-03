package com.todolist.TaskModel

import java.util.*

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 */
object TaskStorage {

    /**
     * An array of items.
     */
    val ITEMS: MutableList<TaskItem> = ArrayList()

    /**
     * A map of items, by ID.
     */
    val ITEM_MAP: MutableMap<String, TaskItem> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createTaskItem(i))
        }
    }

    private fun addItem(item: TaskItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createTaskItem(position: Int): TaskItem {
        return TaskItem(
            position.toString(),
            "Task nr $position",
            Date(),
            position % 3 == 0)
    }

    //private fun makeDetails(position: Int): String {
    //    val builder = StringBuilder()
    //    builder.append("Details about Item: ").append(position)
    //    for (i in 0..position - 1) {
    //        builder.append("\nMore details information here.")
    //    }
    //    return builder.toString()
    //}

    /**
     * A placeholder item representing a piece of content.
     */
    data class TaskItem(
        val id: String,
        var name: String,
        val date: Date,
        var done: Boolean)
    {
        override fun toString(): String = "$date | $name"
    }
}