package donezo.commands;

import java.io.IOException;

import donezo.TaskList;
import donezo.exceptions.DonezoException;


/**
 * Represents a Delete command that can be executed within the application.
 * This class serves as a base for specific command implementations,
 */
public class DeleteCommand extends Command {

    /**
     * Executes the "delete" command by removing a specified task from the task list
     * and updating the task list and file storage accordingly.
     * Throws an exception if the task index provided is invalid.
     *
     * @param userInput the full command input from the user, including the task index to be deleted
     * @param taskList the task list containing the tasks to be managed
     * @throws DonezoException if the task index is invalid or if an error occurs while updating the storage file
     */
    @Override
    public void executeCommand(String userInput, TaskList taskList) throws DonezoException {
        assertCheck(userInput, taskList);

        int taskNdx = Integer.parseInt(userInput.split(" ")[1]) - 1;
        if (taskNdx > taskList.getSizeTaskList()) {
            throw new DonezoException(
                    "Sorry boss, that task does not exist. Try using 'list' to see the index of the task again!");
        }
        
        ui.printDeleteTask(taskList.getTask(taskNdx));
        taskList.removeTask(taskNdx);

        try {
            storage.deleteFromFile(storage.getFilePath(), taskList);
        } catch (IOException e) {
            throw new DonezoException("Whoops unable to delete file!");
        }
    }
    
}
