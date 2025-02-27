package task_tracker_kotlin

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TaskTest {

    @Test
    fun toJsonTest() {
        // given
        val task = Task(1, "New task")

        // when
        val actual = task.toJson()

        // then
        val actualId = actual.substringAfter("id\":").substringBefore(",").trim().toInt()
        val actualDescription = actual.substringAfter("description\": \"").substringBefore("\",").trim()
        val actualStatus = actual.substringAfter("status\": \"").substringBefore("\",").trim()
        assertEquals(1, actualId)
        assertEquals("New task", actualDescription)
        assertEquals("todo", actualStatus)
    }

    @Test
    fun fromJsonTest() {
        // given
        val json = """
            {
                "id": 1,
                "description": "New task",
                "status": "todo",
                "createdAt": "2021-09-01T00:00:00",
                "updatedAt": "2021-09-01T00:00:00"
            }
        """.trimIndent()

        // when
        val actual = Task.fromJson(json)

        // then
        assertEquals(1, actual.id)
        assertEquals("New task", actual.description)
        assertEquals(Task.StatusType.TODO, actual.status)
    }
}