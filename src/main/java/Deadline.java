public class Deadline extends Task {
    protected String endDate;

    public Deadline(String description, String by) {
        super(description);
        this.endDate = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + endDate + ")";
    }
}
