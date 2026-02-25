/**
 * This class is for tasks that are held within a specific time or date range.
 * This class extends from the {@code Task} class by including the /from and /to time and date.
 */
public class Event extends Task {
    protected String fromPeriod;
    protected String toPeriod;

    /**
     * Constructor for an instance of an {@code Event} class.
     *
     * @param description The description of the task.
     * @param startPeriod The starting time or date of the task.
     * @param endPeriod The ending time or date of the task.
     */
    public Event(String description, String startPeriod, String endPeriod) {
        super(description);
        this.fromPeriod = startPeriod;
        this.toPeriod = endPeriod;
    }

    /**
     * Returns a string representation of the event task in the form:
     * [E][<completion status>] <description> /from <time or date> /to <time or date>
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + fromPeriod + " to:" + toPeriod + ")";
    }
}
