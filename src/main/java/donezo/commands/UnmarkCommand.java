package donezo.commands;

import java.io.IOException;

import donezo.TaskList;
import donezo.tasks.Task;

/**
 * Represents an Unmark command that can be executed within the application.
 * This class serves as a base for specific command implementations,
 */
public class UnmarkCommand extends Command {

    /**
     * Executes the "unmark" command by marking a specified task as incomplete
     * and updating the corresponding task list and file storage.
     *
     * @param userInput the full command input from the user, including the task index to be unmarked
     * @param taskList the task list containing the tasks to be updated
     */
    @Override
    public void executeCommand(String userInput, TaskList taskList) {
        int taskNdx = Integer.parseInt(userInput.split(" ")[1]) - 1;
        Task affectedTask = taskList.getTask(taskNdx);
        affectedTask.setDone(false);
        ui.unmarkTaskComplete();
        System.out.println(affectedTask.toString());

        try {
            storage.deleteFromFile(storage.getFilePath(), taskList);
        } catch (IOException e) {
            ui.genericErrorMsg();
        }
    }
    
}
