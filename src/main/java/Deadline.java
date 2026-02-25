/**
 * This class is for tasks that need to be done by a certain time or date.
 * This class extends from the {@code Task} class by including an end date functionality.
 */
public class Deadline extends Task {

    /**
     * The time or date by when the task must be completed.
     */
    protected String endDate;

    /**
     * Constructor for an instance of {@code Deadline} class.
     *
     * @param description The description of the task.
     * @param by The time or date by when the task must be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        this.endDate = by;
    }

    /**
     * Returns a string representation of the deadline task in the form:
     * [D][<completion status>] <description> /by <time or date>
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + endDate + ")";
    }
}
