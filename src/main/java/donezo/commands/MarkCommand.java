package donezo.commands;

import java.io.IOException;

import donezo.lists.ItemList;
import donezo.lists.TaskList;
import donezo.tasks.Task;

/**
 * Represents a Mark command that can be executed within the application.
 * This class serves as a base for specific command implementations,
 */
public class MarkCommand extends Command {

    /**
     * Executes the "mark" command by marking a specified task as complete
     * and updating the corresponding task list and file taskStorage.
     *
     * @param userInput the full command input from the user, including the task index to be marked
     * @param itemList  the task list containing the tasks to be updated
     */
    @Override
    public void executeCommand(String userInput, ItemList itemList) {
        assertCheck(userInput, itemList);

        int taskNdx = Integer.parseInt(userInput.split(" ")[1]) - 1;
        Task affectedTask = (Task) itemList.getItem(taskNdx);
        affectedTask.setDone(true);
        ui.markTaskComplete(affectedTask);

        try {
            taskStorage.deleteFromFile(taskStorage.getFilePath(), (TaskList) itemList);
        } catch (IOException e) {
            ui.printGenericErrorMsg();
        }
    }
    
}
