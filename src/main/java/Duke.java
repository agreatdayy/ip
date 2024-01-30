import Exceptions.DukeException;
import Exceptions.InvalidTaskNameException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String[] COMMANDS = {"help", "list", "todo", "deadline", "event",
            "mark", "unmark", "delete", "bye"};

    public static void main(String[] args) {
        String logo = "                             -------,\n"
                + "                            |   --  |\n"
                + "                            |  |  | |\n"
                + " ---- -   -  ---  ---  ---  |   --  |\n"
                + "|     |   | |   |  |  |   | |  ,----'\n"
                + "|     |---| |---   |   `-_  |  |     \n"
                + "|     |   | | \\    |  |   | |  |     \n"
                + " ---- -   - -  -  ---  ---   --      \n";
        String horizontalLine = "_____________________________________________________";
        String introduction = "\nOink Oink!\nI'm Chris P Bacon but you can call me ChrisP! Oink!\n"
                + "What can I do for you today? :D\n"
                + "~Type 'help' for more command info~\n";
        String help = "Oink! Here are the Command Words:\n'list' - displays the list of task\n"
                + "'todo ...' - to add new task\n'deadline ... /by ...' - to add task with deadline\n"
                + "'event ... /from ... /to ...' - to add an event\n"
                + "'mark <task no.>' - to mark a task done\n'unmark <task no.>' - to unmark a task\n"
                + "'delete <task no.>' - to delete a task\n'bye' - to exit the chatbot";

        ArrayList<Task> taskList = new ArrayList<Task>(100);
        int listCount = 0;
        Scanner scan = new Scanner(System.in);

        System.out.println(logo + horizontalLine
                + introduction + horizontalLine);

        // Get user's first input.
        String userInput = "";
        userInput = scan.nextLine();

        while (!userInput.equals(COMMANDS[8])) {
            try {
                System.out.println(horizontalLine);

                int len = userInput.length();
                boolean isTodo = userInput.startsWith("todo");
                boolean isDeadline = userInput.startsWith("deadline");
                boolean isEvent = userInput.startsWith("event");

                // If input = "help", list down the command words.
                if (userInput.startsWith("help")) {
                    System.out.println(help);

                } else if (userInput.startsWith("list")) {
                    if (listCount == 0) {
                        System.out.println("Oink! There are no tasks! Yeehaww");

                    } else {
                        System.out.println("Oink! Here are the tasks:");
                        for (int i = 1; i <= listCount; i++) {
                            System.out.println(i + ". " + taskList.get(i - 1));
                        }
                    }
                } else if (userInput.startsWith("mark")) {
                    int num = userInput.charAt(5) - '0';
                    taskList.get(num - 1).markDone();

                } else if (userInput.startsWith("unmark")) {
                    int num = userInput.charAt(7) - '0';
                    taskList.get(num - 1).markUndone();

                } else if (userInput.startsWith("delete")) {
                    int num = userInput.charAt(7) - '0';
                    listCount = taskList.get(num - 1).printDeleteTask(listCount);
                    taskList.remove(num - 1);

                } else if (isTodo || isDeadline || isEvent) {
                    if (isTodo) {
                        // Adds a new todo task to the list.
                        if (len < 6) {
                            // If user did not input task name.
                            throw new InvalidTaskNameException("Ooink oink! What's the name of your task?\n"
                                    + " >> todo ...");
                        } else {
                            Todo t = new Todo(userInput.substring(5));
                            taskList.add(t);
                            listCount = t.printAddTask(listCount);
                        }
                    } else if (isDeadline) {
                        //Adds a new deadline task to the list.
                        int idx = userInput.lastIndexOf("/by");
                        boolean isWrongInput = len < 10 || idx < 0 || len < idx + 4;
                        if (isWrongInput) {
                            // If user did not input task description.
                            throw new InvalidTaskNameException("Ooink oink! Please describe your deadline >.<\n"
                                    + " >> deadline ... /by ...");
                        } else {
                            String name = userInput.substring(9, idx - 1);
                            String date = userInput.substring(idx + 4);
                            Deadline d = new Deadline(name, date);
                            taskList.add(d);
                            listCount = d.printAddTask(listCount);
                        }
                    } else {
                        // Adds a new event to the list.
                        int fromIdx = userInput.lastIndexOf("/from");
                        int toIdx = userInput.lastIndexOf("/to");
                        boolean isWrongInput = len < 7 || fromIdx < 0 || toIdx < 0
                                || len < fromIdx + 6 || len < toIdx + 4;
                        if (isWrongInput) {
                            // If user did not input task description.
                            throw new InvalidTaskNameException("Ooink oink! Please describe your event >.<\n"
                                    + " >> event ... /from ... /to ...");
                        } else {
                            String name = userInput.substring(6, userInput.lastIndexOf("/from") - 1);
                            String from = userInput.substring(userInput.lastIndexOf("/from") + 6,
                                    userInput.lastIndexOf("/to") - 1);
                            String to = userInput.substring(userInput.lastIndexOf("/to") + 4);
                            Event e = new Event(name, from, to);
                            taskList.add(e);
                            listCount = e.printAddTask(listCount);
                        }
                    }
                } else {
                    // if user entered input that cannot be recognised.
                    throw new DukeException("Ooink oink! I'm sorry, I don't understand.\nType 'help' for command info!");
                }
            } catch (DukeException | InvalidTaskNameException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(horizontalLine);
            userInput = scan.nextLine(); // Scan for next input.
        }

        // if user entered "bye", close scanner and exit chatbot.
        scan.close();
        System.out.println(horizontalLine + "\nOink! Okie byee... See you soon! :)\n"
                + horizontalLine);
    }
}
