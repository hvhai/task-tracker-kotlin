package task_tracker_kotlin

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.assertEquals

class TaskManagerTest {
    @Test
    @Order(1)
    fun addTaskTest() {
        // given
        val taskManager = TaskManager("test-db.json")

        // when
        taskManager.addTask("New task 1")
        taskManager.addTask("New task 2")

        taskManager.storeAllTasks()

        // then
        val actual = taskManager.getTask(1)
        assertEquals(1, actual?.id)
    }

    @Test
    @Order(2)
    fun loadAllTasksTest() {
        // given
        val taskManager = TaskManager("test-db.json")

        // when
        val actual = taskManager.getAllTasks()

        // then
        assertEquals(2, actual.size)
        assertEquals(1, actual[0].id)
        assertEquals("New task 1", actual[0].description)
        assertEquals(2, actual[1].id)
        assertEquals("New task 2", actual[1].description)
    }

    companion object {
        @JvmStatic
        @AfterAll
        fun cleanup(): Unit {
            Files.deleteIfExists(Path.of("test-db.json"))
        }
    }
}