package donezo.commands;

import java.io.IOException;

import donezo.TaskList;
import donezo.exceptions.DonezoException;
import donezo.tasks.Todo;

/**
 * Represents a Todo command that can be executed within the application.
 * This class serves as a base for specific command implementations,
 */
public class TodoCommand extends Command {
    /**
     * Executes the "todo" command by extracting a task description from the user input,
     * creating a Todo task, and adding it to the given task list.
     * It also handles storage updates and provides appropriate feedback to the user.
     *
     * @param userInput the full command input from the user, including the task description
     * @param taskList the task list where the new todo task will be added
     * @throws DonezoException if the task description is missing or empty
     */
    @Override
    public void executeCommand(String userInput, TaskList taskList) throws DonezoException {
        assertCheck(userInput, taskList);

        String todoDesc = userInput.substring(5).trim();
        if (todoDesc.isBlank()) {
            throw new DonezoException("Hey boss, the description for this task can't be empty!");
        }

        Todo todoTask = new Todo(userInput.substring(5).trim());
        taskList.addTask(todoTask);
        ui.printAddTask(todoTask);

        try {
            storage.writeToFile(storage.getFilePath(), todoTask.toString());
        } catch (IOException e) {
            ui.printUnableToSaveTaskFile();
        }
    }
}
