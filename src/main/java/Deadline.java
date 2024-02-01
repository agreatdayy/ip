import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadlines are tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected String name = "";
    protected LocalDate deadline = null;

    /**
     * Constructor for a Taskline object.
     *
     * @param name task name
     * @param d deadline
     */
    public Deadline(String name, LocalDate d) {
        super();
        this.name = name;
        this.deadline = d;
    }

    /**
     * Constructor for loading from file.
     *
     * @param description of task
     * @param b isDone
     */
    public Deadline(String description, boolean b) {
        super(b);
        int idx = description.indexOf("(by: ");
        this.name = description.substring(0, idx - 1);
        String date = description.substring(idx + 5, description.length() - 1);
        this.deadline = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * toString method for printing task description.
     * @return task status + task type + task name + deadline
     */
    @Override
    public String toString() {
        return super.toString() + "[D] " + this.name
                + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
