public class Task {
    protected String taskName;
    protected boolean isTaskDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isTaskDone = false;
    }


    public void setTaskStatus(boolean status) {
        this.isTaskDone = status;
    }

    public char getTaskStatusSymbol() {
        if (isTaskDone) { return 'X'; }
        return ' ';
    }
}
