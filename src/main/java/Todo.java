/**
 * This class is for tasks that have no time and date constraints.
 * This class extends from the {@code Task} class.
 */
public class Todo extends Task {

    /**
     * Constructor for an instance of {@code Todo} class.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task in the form:
     * [T][<completion status>] <description>.
     *
     * @return A formatted string representing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
