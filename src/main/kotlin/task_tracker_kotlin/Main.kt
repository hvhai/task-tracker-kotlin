package task_tracker_kotlin


fun main() {
    TaskTrackerCLI(TaskManager()).start()
    println("All data saved!")
}