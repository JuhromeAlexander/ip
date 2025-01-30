package donezo;

import donezo.commands.*;
import donezo.exceptions.DonezoException;
import donezo.parser.Parser;

/**
 * The Donezo class represents a command-line task manager application.
 * It provides functionality to manage a user's task list, including adding,
 * marking, unmarking, deleting tasks, and listing current tasks.
 *
 */
public class Donezo {
    public static void main(String[] args) {
        Donezo donezo = new Donezo();
        try {
            donezo.run();
        } catch (DonezoException e) {
            System.out.println(e);
        }
    }

    /**
     * Executes the primary workflow of the application. This method initializes key components
     * required for the application's operations, including the user interface (UI), storage,
     * task list, and command parser. The main loop manages user inputs and delegates actions
     * to the appropriate commands, enabling tasks to be listed, marked/unmarked, added, or deleted.
     *
     * The workflow continues until the user inputs "bye", at which point the application
     * terminates gracefully by closing resources and displaying an exit message.
     *
     * Commands are classified into task-related operations such as "list", "mark", "unmark",
     * "delete", "deadline", "event", and "todo". Each command processes user input for the
     * corresponding task operation. Unrecognized commands prompt the user to enter a valid command.
     *
     * A DonezoException may be thrown for invalid operations, particularly during task additions
     * (deadline, event, todo) or task deletions. Exception messages are displayed to the user as part
     * of handling these errors.
     *
     * @throws DonezoException if an error occurs while loading the task list from the file
     * or during specific command executions that involve invalid task operations.
     */
    private void run() throws DonezoException {
        UI ui = new UI();
        Storage storageActual = new Storage("tasks.txt");
        TaskList taskListActual = storageActual.loadFromFile();
        Parser parser = new Parser();

        int numTasks = taskListActual.getSizeTaskList();

        System.out.println(ui.greetUser());
        String userInput = ui.nextLine();
        
        while (!userInput.equals("bye")) {
            String taskType = parser.parseCommand(userInput);
            switch (taskType.toLowerCase()) {
            case "list":
                ListCommand listCommand = new ListCommand();
                listCommand.executeCommand(userInput, taskListActual);
                userInput = ui.nextLine();
                break;
            
            case "mark":
                MarkCommand markCommand = new MarkCommand();
                markCommand.executeCommand(userInput, taskListActual);
                userInput = ui.nextLine();
                break;
            
            case "unmark":
                UnmarkCommand unmarkCommand = new UnmarkCommand();
                unmarkCommand.executeCommand(userInput, taskListActual);
                userInput = ui.nextLine();
                break;

            case "delete":
                DeleteCommand deleteCommand = new DeleteCommand();
                try {
                    deleteCommand.executeCommand(userInput, taskListActual);
                    numTasks--;
                    ui.printNumTasks(numTasks);
                } catch (DonezoException e) {
                    ui.printDonezoExceptionMessage(e);
                }
                userInput = ui.nextLine();
                break;
            
            case "deadline":
                DeadlineCommand deadlineCommand = new DeadlineCommand();
                try {
                    deadlineCommand.executeCommand(userInput, taskListActual);
                    numTasks++;
                    ui.printNumTasks(numTasks);
                } catch (DonezoException e) {
                    ui.printDonezoExceptionMessage(e);
                }
                userInput = ui.nextLine();
                break;
            
            case "event":
                EventCommand eventCommand = new EventCommand();
                try {
                    eventCommand.executeCommand(userInput, taskListActual);
                    numTasks++;
                    ui.printNumTasks(numTasks);
                } catch (DonezoException e) {
                    ui.printDonezoExceptionMessage(e);
                }
                userInput = ui.nextLine();
                break;

            case "todo":
                TodoCommand todoCommand = new TodoCommand();
                try {
                    todoCommand.executeCommand(userInput, taskListActual);
                    numTasks++;
                    ui.printNumTasks(numTasks);
                } catch (DonezoException e) {
                    ui.printDonezoExceptionMessage(e);
                }
                userInput = ui.nextLine();
                break;
            
            default:
                ui.printTryCommandAgain();
                userInput = ui.nextLine();
            }
        }
        System.out.println(ui.sayBye());
        ui.closeInput();

    }
}
