package seedu.task;

import java.util.ArrayList;

/**
 * This class contains a list of the tasks added by the user.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class with a new task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList class with an existing task list.
     *
     * @param tasks An existing task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int idx) {
        return this.tasks.get(idx);
    }

    /**
     * Checks if task list is empty.
     *
     * @return True if list is empty.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Removes a task from the list and returns the task details.
     *
     * @param idx The task number.
     * @return The message to be printed by ui.
     */
    public String deleteTask(int idx) {
        int taskCount = this.getSize() - 1;
        String message = "Oink! Yosh I have removed this task:\n"
                + " >> " + this.getTask(idx) + "\nOink's task count: " + taskCount + "\n";
        this.tasks.remove(idx);
        return message;
    }

    /**
     * Adds a task to the list and returns the task details.
     *
     * @param task The new task to be added.
     * @return The message to be printed by ui.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return "Oink! Nice I have added this task:\n"
                + " >> " + task + "\nOink's task count: " + this.getSize() + "\n";
    }

    /**
     * Marks the task done and returns the task details.
     *
     * @param idx The task number.
     * @return The message to be printed by ui.
     */
    public String markTask(int idx) {
        this.getTask(idx).markDone();
        return "Oink! You have completed this task! Nice nice nice\n"
                + " >> " + this.getTask(idx) + "\n";
    }

    /**
     * Marks the task done and returns the task details.
     *
     * @param idx The task number.
     * @return The message to be printed by ui.
     */
    public String unmarkTask(int idx) {
        this.getTask(idx).markUndone();
        return "Oink! You have unmarked this task! Why why why\n"
                + " >> " + this.getTask(idx) + "\n";
    }
}
