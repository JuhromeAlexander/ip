package donezo.commands;

import java.io.IOException;

import donezo.TaskList;
import donezo.tasks.Task;

/**
 * Represents a Mark command that can be executed within the application.
 * This class serves as a base for specific command implementations,
 */
public class MarkCommand extends Command {

    /**
     * Executes the "mark" command by marking a specified task as complete
     * and updating the corresponding task list and file storage.
     *
     * @param userInput the full command input from the user, including the task index to be marked
     * @param taskList the task list containing the tasks to be updated
     */
    @Override
    public void executeCommand(String userInput, TaskList taskList) {
        assertCheck(userInput, taskList);

        int taskNdx = Integer.parseInt(userInput.split(" ")[1]) - 1;
        Task affectedTask = taskList.getTask(taskNdx);
        affectedTask.setDone(true);
        ui.markTaskComplete(affectedTask);

        try {
            storage.deleteFromFile(storage.getFilePath(), taskList);
        } catch (IOException e) {
            ui.printGenericErrorMsg();
        }
    }
    
}
