public class Event extends Task {
    protected String fromTime;
    protected String toTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.fromTime = startTime;
        this.toTime = endTime;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + fromTime + ", to: " + toTime + ")";
    }
}
