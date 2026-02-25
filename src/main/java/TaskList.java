import java.util.ArrayList;

/**
 * Generates the list {@code taskArrayList} and provides all operations for modifying the list.
 *
 */
public class TaskList {

    /**
     * The {@code ArrayList} data structure storing all the {@code Task} objects
     */
    private final ArrayList<Task> taskArrayList;

    /**
     * Constructor to create an instance of {@code TaskList} with {@code maxNumberOfTasks} size.
     *
     * @param maxNumberOfTasks The size of the list.
     */
    public TaskList(int maxNumberOfTasks) {
        this.taskArrayList = new ArrayList<>(maxNumberOfTasks);
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The {@code Task} object to be added.
     */
    public void addTask(Task task) {
        this.taskArrayList.add(task);
    }

    /**
     * Deletes a task from the list based on its task number.
     *
     * @param taskNumber The 1-based index of the task to be deleted.
     * @return The {@code Task} object that was deleted.
     */
    public Task deleteTask(int taskNumber) {
        return taskArrayList.remove(taskNumber - 1);
    }

    /**
     * Marks a task as completed, based on its displayed task number.
     *
     * @param taskNumber The 1-based index of the task that was marked as completed.
     */
    public void markTask(int taskNumber) {
        taskArrayList.get(taskNumber - 1).setTaskStatus(true);
    }

    /**
     * Unmarks a task as not completed, based on its displayed task number.
     *
     * @param taskNumber The 1-based index of the task that was unmarked as not completed.
     */
    public void unMarkTask(int taskNumber) {
        taskArrayList.get(taskNumber - 1).setTaskStatus(false);
    }

    /**
     * Returns a task from the list as specified by the task's number.
     *
     * @param taskNumber The 1-based index of the task to retrieve.
     * @return The requested {@code Task} object.
     */
    public Task getTask(int taskNumber) {
        return taskArrayList.get(taskNumber - 1);
    }

    /**
     * Searches the list for all tasks whose description contains the keyword specified by the user.
     * This search is case-insensitive.
     *
     * @param keyword The string specified by the user.
     * @return An {@code ArrayList} containing all the {@code Task} objects that match the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskArrayList) {
            if (task.taskDescription.toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The {@code ArrayList} object containing the {@code Task} objects.
     */
    public ArrayList<Task> getTaskArrayList() {
        return taskArrayList;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of {@code Task} objects in the list.
     */
    public int getSize() {
        return taskArrayList.size();
    }
}