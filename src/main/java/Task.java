/**
 * This class is used as a blueprint for Task objects
 * which are used in the TaskHandler.java bot
 */
public class Task {
    protected String taskDescription;
    protected boolean isTaskDone;

    public Task(String description) {
        this.taskDescription = description;
        this.isTaskDone = false;
    }

    public void setTaskStatus(boolean status) {
        this.isTaskDone = status;
    }

    /*public char getTaskStatusSymbol() {
        if (isTaskDone) { return 'X'; }
        return ' ';
    }*/

    public String toString() {
        if (isTaskDone) {
            return "[X] " + taskDescription;
        }
        return "[ ] " + taskDescription;
    }
}
