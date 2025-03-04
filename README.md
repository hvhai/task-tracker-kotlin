# Task Tracker CLI with Kotlin
> Sample solution for the [Task Tracker](https://roadmap.sh/projects/task-tracker) challenge from [roadmap.sh](https://roadmap.sh/).

## 🎯 Overview
This is a simple CLI application that allows you to manage tasks. You can add, remove, list, and mark tasks as done. \
All data will store in a JSON file (data.json), ensuring your tasks are saved between sessions.

## 🚀 How to Run
1. Clone the repository:

    ```bash
    git clone https://github.com/hvhai/task-tracker-kotlin.git
    cd task-tracker-kotlin
    ```
2. Run the application:
- Using Gradle:
    ```bash
    ./gradlew --console=plain -q run
    ```
- Using IDE: \
Compile and run `Main.kt` file.

## 📘 Usage Example

```bash
task-cli new "my new task"
Invalid command
task-cli add my new task
Invalid input. Please use the following pattern: add "<description>"
task-cli add "my new task"
 Task added successfully (ID: 6)
task-cli list
1 - new task from CLI - TODO
2 - updated content - DONE
3 - task from second run - TODO
4 - test - TODO
5 - task updates - DONE
6 - my new task - TODO
task-cli list todo
1 - new task from CLI
3 - task from second run
4 - test
6 - my new task
task-cli update 6 "my updated task"
task-cli list todo
1 - new task from CLI
3 - task from second run
4 - test
6 - my updated task
task-cli mark-in-progress 6
task-cli list in-progress
6 - my updated task
task-cli list
1 - new task from CLI - TODO
2 - updated content - DONE
3 - task from second run - TODO
4 - test - TODO
5 - task updates - DONE
6 - my updated task - IN_PROGRESS
task-cli mark done 6
Invalid command
task-cli mark-done 6
task-cli list done
2 - updated content
5 - task updates
6 - my updated task
task-cli exit
Exiting...
All data saved!

```