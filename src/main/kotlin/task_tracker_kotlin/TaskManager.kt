package task_tracker_kotlin

import java.nio.file.Paths

class TaskManager(val filename: String = "data.json") {
    private val tasks = mutableListOf<Task>()

    init {
        val path = Paths.get(filename)
        if (!path.toFile().exists()) {
            path.toFile().createNewFile()
        }
        val content = path.toFile().readText()
        content.replaceFirst("[", "").replaceAfterLast("]", "")
        content.split("},\n").forEach {
            if (it.isNotEmpty()) {
                val task = Task.fromJson("$it}")
                tasks.add(task)
            }
        }
    }

    fun storeAllTasks() {
        val path = Paths.get(filename)
        val content = tasks.joinToString(",\n") { it.toJson() }
        path.toFile().writeText("[$content]")
    }

    fun addTask(description: String): Task {
        val id = if (tasks.isEmpty()) 1 else tasks[tasks.size - 1].id + 1
        val task = Task(id, description)
        tasks.add(task)
        return task
    }

    fun getTask(id: Int): Task? {
        return tasks.find { it.id == id }
    }

    fun getAllTasks(): List<Task> {
        return tasks.toList()
    }

    fun deleteTask(id: Int): Boolean {
        val task = tasks.find { it.id == id }
        return tasks.remove(task)
    }

    fun markDone(id: Int): Boolean {
        val task = tasks.find { it.id == id }
        task?.markDone()
        return task != null
    }

    fun markInProgress(id: Int): Boolean {
        val task = tasks.find { it.id == id }
        task?.markInProgress()
        return task != null
    }

    fun updateDescription(id: Int, description: String): Boolean {
        val task = tasks.find { it.id == id }
        task?.updateDescription(description)
        return task != null
    }
}