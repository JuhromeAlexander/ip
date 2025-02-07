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
import donezo.parser.Parser;
import donezo.ui.CommandLineUI;
import donezo.ui.GraphicalUI;
import donezo.ui.UI;

/**
 * The Donezo class represents a command-line task manager application.
 * It provides functionality to manage a user's task list, including adding,
 * marking, unmarking, deleting tasks, and listing current tasks.
 *
 */
public class Donezo {

    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private UI ui;
    private int numTasks;

    public Donezo(UI ui) throws DonezoException {
        this.ui = ui;
        parser = new Parser();
        storage = new Storage("tasks.txt");
        taskList = storage.loadFromFile();
        numTasks = taskList.getSizeTaskList();
    }

    /**
     * Processes the user input command and returns a response string.
     * <p>
     * If the {@code userInput} equals "bye", this method will invoke {@code ui.closeInput()}
     * and return the farewell message from {@code ui.sayBye()}. For any other input, the method
     * uses the {@code parser} to determine the command type and creates the corresponding command
     * instance. It then injects the current {@code ui} and {@code storage} instances into the command,
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
                listCommand.setStorage(storage);
                listCommand.executeCommand(userInput, taskList);
                break;

            case "mark":
                MarkCommand markCommand = new MarkCommand();
                markCommand.setUi(ui);
                markCommand.setStorage(storage);
                markCommand.executeCommand(userInput, taskList);
                break;

            case "unmark":
                UnmarkCommand unmarkCommand = new UnmarkCommand();
                unmarkCommand.setUi(ui);
                unmarkCommand.setStorage(storage);
                unmarkCommand.executeCommand(userInput, taskList);
                break;

            case "delete":
                DeleteCommand deleteCommand = new DeleteCommand();
                deleteCommand.setUi(ui);
                deleteCommand.setStorage(storage);
                deleteCommand.executeCommand(userInput, taskList);
                numTasks--;
                ui.printNumTasks(numTasks);
                break;

            case "deadline":
                DeadlineCommand deadlineCommand = new DeadlineCommand();
                deadlineCommand.setUi(ui);
                deadlineCommand.setStorage(storage);
                deadlineCommand.executeCommand(userInput, taskList);
                numTasks++;
                ui.printNumTasks(numTasks);
                break;

            case "event":
                EventCommand eventCommand = new EventCommand();
                eventCommand.setUi(ui);
                eventCommand.setStorage(storage);
                eventCommand.executeCommand(userInput, taskList);
                numTasks++;
                ui.printNumTasks(numTasks);
                break;

            case "todo":
                TodoCommand todoCommand = new TodoCommand();
                todoCommand.setUi(ui);
                todoCommand.setStorage(storage);
                todoCommand.executeCommand(userInput, taskList);
                numTasks++;
                ui.printNumTasks(numTasks);
                break;

            case "find":
                FindCommand findCommand = new FindCommand();
                findCommand.setUi(ui);
                findCommand.setStorage(storage);
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

    public String getGreeting() {
        if (ui instanceof GraphicalUI) {
            ui.greetUser();
            return ((GraphicalUI) ui).getAndClearOutputBuffer();
        }

        return "";
    }

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

    public static void main(String[] args) {
        try {
            Donezo donezo = new Donezo(new CommandLineUI());
            donezo.runCLI();
        } catch (DonezoException e) {
            System.out.println(e.getMessage());
        }
    }


}
