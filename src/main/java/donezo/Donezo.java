package donezo;

import donezo.commands.DeadlineCommand;
import donezo.commands.DeleteCommand;
import donezo.commands.EventCommand;
import donezo.commands.FindCommand;
import donezo.commands.ListCommand;
import donezo.commands.MarkCommand;
import donezo.commands.TodoCommand;
import donezo.commands.UnmarkCommand;
import donezo.exceptions.DonezoException;
import donezo.lists.TaskList;
import donezo.parser.Parser;
import donezo.storage.TaskStorage;
import donezo.ui.CommandLineUI;
import donezo.ui.GraphicalUI;
import donezo.ui.UI;

/**
 * The Donezo class provides the core backend logic for the application.
 * It supports both CLI and GUI modes by using an injected UI implementation.
 * In CLI mode, the main() method runs a command loop; in GUI mode, the getResponse()
 * method is used to process individual commands.
 */
public class Donezo {

    private Parser parser;
    private TaskStorage taskStorage;
    private TaskList taskList;
    private UI ui;
    private int numTasks;

    public Donezo(UI ui) throws DonezoException {
        this.ui = ui;
        parser = new Parser();
        taskStorage = new TaskStorage("tasks.txt");
        taskList = taskStorage.loadFromFile();
        numTasks = taskList.getSizeTaskList();
    }

    /**
     * Processes the user input command and returns a response string.
     * <p>
     * If the {@code userInput} equals "bye", this method will invoke {@code ui.closeInput()}
     * and return the farewell message from {@code ui.sayBye()}. For any other input, the method
     * uses the {@code parser} to determine the command type and creates the corresponding command
     * instance. It then injects the current {@code ui} and {@code taskStorage} instances into the command,
     * executes the command, and performs any necessary updates (such as adjusting the task count and
     * printing the number of tasks).
     * </p>
     *
     * @param userInput the full command input provided by the user.
     * @return a response string generated from processing the command; if the input is "bye", returns the
     *         farewell message; if a {@code DonezoException} occurs, returns the exception message; otherwise,
     *         returns the captured output if in graphical mode, or a default string ("") for non-graphical mode.
     */
    public String getResponse(String userInput) {
        if (userInput.equals("bye")) {
            if (ui instanceof GraphicalUI) {
                ui.closeInput();
                ui.sayBye();
                return ((GraphicalUI) ui).getAndClearOutputBuffer();
            }
        }
        try {
            String commandType = parser.parseCommand(userInput);
            switch (commandType.toLowerCase()) {
            case "list":
                ListCommand listCommand = new ListCommand();
                listCommand.setUi(ui);
                listCommand.setTaskStorage(taskStorage);
                listCommand.executeCommand(userInput, taskList);
                break;

            case "mark":
                MarkCommand markCommand = new MarkCommand();
                markCommand.setUi(ui);
                markCommand.setTaskStorage(taskStorage);
                markCommand.executeCommand(userInput, taskList);
                break;

            case "unmark":
                UnmarkCommand unmarkCommand = new UnmarkCommand();
                unmarkCommand.setUi(ui);
                unmarkCommand.setTaskStorage(taskStorage);
                unmarkCommand.executeCommand(userInput, taskList);
                break;

            case "delete":
                DeleteCommand deleteCommand = new DeleteCommand();
                deleteCommand.setUi(ui);
                deleteCommand.setTaskStorage(taskStorage);
                deleteCommand.executeCommand(userInput, taskList);
                numTasks--;
                ui.printNumTasks(numTasks);
                break;

            case "deadline":
                DeadlineCommand deadlineCommand = new DeadlineCommand();
                deadlineCommand.setUi(ui);
                deadlineCommand.setTaskStorage(taskStorage);
                deadlineCommand.executeCommand(userInput, taskList);
                numTasks++;
                ui.printNumTasks(numTasks);
                break;

            case "event":
                EventCommand eventCommand = new EventCommand();
                eventCommand.setUi(ui);
                eventCommand.setTaskStorage(taskStorage);
                eventCommand.executeCommand(userInput, taskList);
                numTasks++;
                ui.printNumTasks(numTasks);
                break;

            case "todo":
                TodoCommand todoCommand = new TodoCommand();
                todoCommand.setUi(ui);
                todoCommand.setTaskStorage(taskStorage);
                todoCommand.executeCommand(userInput, taskList);
                numTasks++;
                ui.printNumTasks(numTasks);
                break;

            case "find":
                FindCommand findCommand = new FindCommand();
                findCommand.setUi(ui);
                findCommand.setTaskStorage(taskStorage);
                findCommand.executeCommand(userInput, taskList);
                break;

            default:
                ui.printTryCommandAgain();
                break;
            }

            if (ui instanceof GraphicalUI) {
                return ((GraphicalUI) ui).getAndClearOutputBuffer();
            }

            return " ";

        } catch (DonezoException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the greeting message from the UI.
     * <p>
     * In GUI mode (when {@code ui} is an instance of {@code GraphicalUI}),
     * this method calls {@code ui.greetUser()} and returns the captured greeting
     * from the output buffer. In other cases, an empty string is returned.
     * </p>
     *
     * @return the greeting message if in GUI mode; otherwise an empty string.
     */
    public String getGreeting() {
        if (ui instanceof GraphicalUI) {
            ui.greetUser();
            return ((GraphicalUI) ui).getAndClearOutputBuffer();
        }

        return "";
    }

    /**
     * Runs the application in CLI mode.
     * <p>
     * This method prints the greeting message, then enters a loop where it reads
     * user input from the console and processes each command until the user types "bye".
     * After "bye" is entered, the farewell message is printed and input is closed.
     * </p>
     *
     * @throws DonezoException if an error occurs during command processing.
     */
    public void runCLI() throws DonezoException {
        System.out.println(ui.greetUser());
        String userInput = ui.nextLine();

        while (!userInput.equals("bye")) {
            System.out.println(getResponse(userInput));
            userInput = ui.nextLine();
        }

        System.out.println(ui.sayBye());
        ui.closeInput();
    }

    /**
     * The main entry point for running the application in CLI mode.
     * It creates an instance of Donezo with a CommandLineUI, and then calls runCLI()
     * to start processing user input from the console.
     */
    public static void main(String[] args) {
        try {
            Donezo donezo = new Donezo(new CommandLineUI());
            donezo.runCLI();
        } catch (DonezoException e) {
            System.out.println(e.getMessage());
        }
    }


}
