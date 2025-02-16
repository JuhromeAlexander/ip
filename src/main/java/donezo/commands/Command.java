package donezo.commands;

import donezo.lists.ItemList;
import donezo.storage.NoteStorage;
import donezo.storage.TaskStorage;
import donezo.exceptions.DonezoException;
import donezo.ui.UI;

/**
 * Represents an abstract command that can be executed within the application.
 * This class serves as a base for specific command implementations,
 * enforcing a structure for executing commands.
 */
public abstract class Command {
    protected UI ui;
    protected TaskStorage taskStorage;
    protected NoteStorage noteStorage;

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public void setTaskStorage(TaskStorage taskStorage) {
        this.taskStorage = taskStorage;
    }

    public void setNoteStorage(NoteStorage noteStorage) {
        this.noteStorage = noteStorage;
    }

    public void assertCheck(String userInput, ItemList itemList) {
        assert ui != null;
        assert taskStorage != null;
        assert noteStorage != null;
        assert userInput != null;
        assert itemList != null;
    }

    /**
     * Executes the specified command based on the user's input and interacts
     * with the provided task list. Commands vary depending on their specific
     * implementation in subclasses. Each command may modify the task list and
     * handle additional behaviors like file taskStorage updates or user feedback.
     *
     * @param userInput the full command input provided by the user
     * @param itemList  the task list containing tasks to be processed or modified
     * @throws DonezoException if an error occurs while executing the command,
     *                         such as invalid input or missing parameters for the command
     */
    public abstract void executeCommand(String userInput, ItemList itemList) throws DonezoException;
}
