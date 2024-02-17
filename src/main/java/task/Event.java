package task;

/**
 * Events are tasks that start at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {
    // Task.Task description consist of name, start and end of task.
    protected String[] description = new String[3];

    /**
     * Constructor for an Event object.
     *
     * @param description Includes name, event start and event end.
     */
    public Event(String[] description) {
        super();
        for (int i = 0; i < 3; i++) {
            this.description[i] = description[i];
        }
    }

    /**
     * Constructor for loading from file.
     *
     * @param description of the event.
     * @param isDone Whether the task is completed.
     */
    public Event(String description, boolean isDone) {
        super(isDone);
        int fromIdx = description.indexOf("(from:");
        int toIdx = description.indexOf("to:");
        int len = description.length();
        this.description[0] = description.substring(0, fromIdx - 1);
        this.description[1] = description.substring(fromIdx + 7, toIdx - 1);
        this.description[2] = description.substring(toIdx + 4, len - 1);
    }

    @Override
    public String toString() {
        return super.toString() + "[E] " + this.description[0]
                + " (from: " + this.description[1] + " to: " + this.description[2] + ")";
    }
}
