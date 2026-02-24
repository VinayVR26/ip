import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskArrayList;

    public TaskList(int maxNumberOfTasks) {
        this.taskArrayList = new ArrayList<>(maxNumberOfTasks);
    }

    public TaskList(ArrayList<Task> storedTasks) {
        this.taskArrayList = storedTasks;
    }

    public void addTask(Task task) {
        this.taskArrayList.add(task);
    }

    public Task deleteTask(int taskNumber) {
        return taskArrayList.remove(taskNumber - 1);
    }

    public void markTask(int taskNumber) {
        taskArrayList.get(taskNumber - 1).setTaskStatus(true);
    }

    public void unMarkTask(int taskNumber) {
        taskArrayList.get(taskNumber - 1).setTaskStatus(false);
    }

    public Task getTask(int taskNumber) {
        return taskArrayList.get(taskNumber - 1);
    }

    public ArrayList<Task> getTaskArrayList() {
        return taskArrayList;
    }

    public int getSize() {
        return taskArrayList.size();
    }
}
