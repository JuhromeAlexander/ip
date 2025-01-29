package donezo.commands;

import donezo.Storage;
import donezo.TaskList;
import donezo.UI;
import donezo.exceptions.DonezoException;

public abstract  class Command {
    protected UI ui = new UI();
    protected Storage storage = new Storage("tasks.txt");

    public abstract void executeCommand(String userInput, TaskList taskList) throws DonezoException;
}
