package task_tracker_kotlin

import java.util.Scanner

class TaskTrackerCLI(val taskManager: TaskManager) {
    fun start() {
        try {
            print("task-cli ")

            val input = Scanner(System.`in`).nextLine()
            requireNotNull(input) { "Invalid input" }
            val command = input.split(" ")[0]
            when (command) {
                "add" -> addTask(input)
                "update" -> updateTask(input)
                "delete" -> deleteTask(input)
                "mark-done" -> markDone(input)
                "mark-in-progress" -> markInProgress(input)
                "list" ->
                    if (input.split(" ").size < 2) {
                        listAllTasks()
                    } else
                        when (input.split(" ")[1]) {
                            "done" -> listTasksDone()
                            "todo" -> listTasksTodo()
                            "in-progress" -> listTasksInProgress()
                            else -> println("Invalid command")
                        }

                "exit" -> {
                    println("Exiting...")
                    taskManager.storeAllTasks()
                    return
                }

                else -> println("Invalid command")
            }
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
        start()
    }

    private fun addTask(input: String) {
        // validate that the input is in the pattern: add<space>"<description>" using regex
        require(input.matches(Regex("add\\s\".*\""))) {
            "Invalid input. Please use the following pattern: add \"<description>\""
        }
        val description = input.substringAfter("add \"").substringBefore("\"").trim()
        val newTask = taskManager.addTask(description)
        println(" Task added successfully (ID: ${newTask.id})")
    }

    private fun updateTask(input: String) {
        // validate that the input is in the pattern: update<space><id><space>"<description>" using regex
        require(input.matches(Regex("update\\s\\d+\\s\".*\""))) {
            "Invalid input. Please use the following pattern: update <id> \"<description>\""
        }

        val id = input.substringAfter("update ").substringBefore(" ").toInt()
        val description = input.substringAfter(" \"").substringBefore("\"").trim()
        taskManager.updateDescription(id, description)
    }

    private fun deleteTask(input: String) {
        // validate that the input is in the pattern: delete<space><id> using regex
        require(input.matches(Regex("delete\\s\\d+"))) {
            "Invalid input. Please use the following pattern: delete <id>"
        }
        val id = input.substringAfter("delete ").toInt()
        taskManager.deleteTask(id)
    }

    private fun markDone(input: String) {
        // validate that the input is in the pattern: mark-done<space><id> using regex
        require(input.matches(Regex("mark-done\\s\\d+"))) {
            "Invalid input. Please use the following pattern: mark-done <id>"
        }
        val id = input.substringAfter("mark-done ").toInt()
        taskManager.markDone(id)
    }

    private fun markInProgress(input: String) {
        // validate that the input is in the pattern: mark-in-progress<space><id> using regex
        require(input.matches(Regex("mark-in-progress\\s\\d+"))) {
            "Invalid input. Please use the following pattern: mark-in-progress <id>"
        }
        val id = input.substringAfter("mark-in-progress ").toInt()
        taskManager.markInProgress(id)
    }

    private fun listTasksDone() {
        showListTasksByStatus(Task.StatusType.DONE)
    }

    private fun listTasksTodo() {
        showListTasksByStatus(Task.StatusType.TODO)
    }


    private fun listTasksInProgress() {
        showListTasksByStatus(Task.StatusType.IN_PROGRESS)
    }

    private fun showListTasksByStatus(status: Task.StatusType) {
        taskManager.getAllTasks().filter { it.status == status }.forEach {
            println("${it.id} - ${it.description}")
        }
    }

    private fun listAllTasks() {
        taskManager.getAllTasks().forEach {
            println("${it.id} - ${it.description} - ${it.status}")
        }
    }
}