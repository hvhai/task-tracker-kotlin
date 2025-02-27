package task_tracker_kotlin

import java.time.LocalDateTime

class Task(
    val id: Int,
    var description: String,
    var status: StatusType = StatusType.TODO,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    enum class StatusType(val value: String) {
        TODO("todo"),
        IN_PROGRESS("in-progress"),
        DONE("done");

        companion object {
            fun fromValue(value: String): StatusType {
                return when (value) {
                    "todo" -> TODO
                    "in-progress" -> IN_PROGRESS
                    "done" -> DONE
                    else -> throw IllegalArgumentException("Invalid status value")
                }
            }
        }
    }

    fun markDone() {
        this.status = StatusType.DONE
        this.updatedAt = LocalDateTime.now()
    }

    fun markInProgress() {
        this.status = StatusType.IN_PROGRESS
        this.updatedAt = LocalDateTime.now()
    }

    fun updateDescription(description: String) {
        this.description = description
        this.updatedAt = LocalDateTime.now()
    }

    fun toJson(): String {
        return """
            {
                "id": $id,
                "description": "$description",
                "status": "${status.value}",
                "createdAt": "${createdAt}",
                "updatedAt": "${updatedAt}"
            }
        """.trimIndent()
    }

    companion object {
        @JvmStatic
        fun fromJson(content: String): Task {
            val json = content.trimIndent()
            val id = json.substringAfter("id\":").substringBefore(",").trim().toInt()
            val description = json.substringAfter("description\": \"").substringBefore("\",").trim()
            val status = json.substringAfter("status\": \"").substringBefore("\",").trim()
            val createdAt = json.substringAfter("createdAt\": \"").substringBefore("\",").trim()
            val updatedAt = json.substringAfter("updatedAt\": \"").substringBefore("\"").trim()
            return Task(
                id,
                description,
                StatusType.fromValue(status),
                LocalDateTime.parse(createdAt),
                LocalDateTime.parse(updatedAt)
            )
        }
    }
}