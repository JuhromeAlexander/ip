package donezo.commands;

import donezo.Storage;
import donezo.TaskList;
import donezo.UI;
import donezo.exceptions.DonezoException;

/**
 * Represents an abstract command that can be executed within the application.
 * This class serves as a base for specific command implementations,
 * enforcing a structure for executing commands.
 */
public abstract class Command {
    protected UI ui = new UI();
    protected Storage storage = new Storage("tasks.txt");

    /**
     * Executes the specified command based on the user's input and interacts
     * with the provided task list. Commands vary depending on their specific
     * implementation in subclasses. Each command may modify the task list and
     * handle additional behaviors like file storage updates or user feedback.
     *
     * @param userInput the full command input provided by the user
     * @param taskList the task list containing tasks to be processed or modified
     * @throws DonezoException if an error occurs while executing the command,
     *                         such as invalid input or missing parameters for the command
     */
    public abstract void executeCommand(String userInput, TaskList taskList) throws DonezoException;
}
