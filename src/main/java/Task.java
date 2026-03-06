/**
 * This class is used as a blueprint for all task types. Common data among all task types such as description and
 * completion status are stored here.
 */
public class Task {

    /**
     * The description of the task.
     */
    protected String taskDescription;

    /**
     * Whether the task has been completed or not.
     */
    protected boolean isTaskDone;

    /**
     * Constructor to create an instance of the {@code Task} with a given description.
     * The task is marked as not done by default.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.taskDescription = description;
        this.isTaskDone = false;
    }

    /**
     * Updates the completion status of the task.
     *
     * @param status If {@code true}, the task is marked as done. If {@code false} the task is unmarked.
     */
    public void setTaskStatus(boolean status) {
        this.isTaskDone = status;
    }

    /**
     * Returns a string representation of the task's completion status concatenated with the description.
     *
     * @return A string in the form "[X] <task description>" if the task is completed or
     *         "[ ] <task description>" if the task is not completed.
     */
    @Override
    public String toString() {
        if (isTaskDone) {
            return "[X] " + taskDescription;
        }
        return "[ ] " + taskDescription;
    }
}
