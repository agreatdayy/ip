package seedu.chrisPBacon;

import seedu.task.TaskList;


import java.io.FileNotFoundException;

/**
 *  This class contains the main method for the chatbot, ChrisP Bacon.
 *  ChrisP Bacon is a chatbot that manages the user's list of tasks.
 */
public class ChrisPBacon {
    private final seedu.chrisPBacon.Storage storage;
    private TaskList tasks;

    /**
     * Initialises new ui and storage objects, loads tasks into a new task list object.
     *
     * @param filePath of the saved task list
     */
    public ChrisPBacon(String filePath) {
        Ui ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.printError("Oink! File not found :(\n");
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot program.
     */
    public void run() {
        Parser parser = new Parser();
        parser.parse(this.tasks);
        storage.save(this.tasks);
    }

    /**
     * Initialises and run chris p bacon program.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        new ChrisPBacon("data/list.txt").run();
    }
}
