package donezo.commands;

import java.io.IOException;

import donezo.exceptions.DonezoException;
import donezo.lists.ItemList;
import donezo.lists.TaskList;
import donezo.tasks.Task;

/**
 * Represents an Unmark command that can be executed within the application.
 * This class serves as a base for specific command implementations,
 */
public class UnmarkCommand extends Command {

    /**
     * Executes the "unmark" command by marking a specified task as incomplete
     * and updating the corresponding task list and file taskStorage.
     *
     * @param userInput the full command input from the user, including the task index to be unmarked
     * @param itemList  the task list containing the tasks to be updated
     */
    @Override
    public void executeCommand(String userInput, ItemList itemList) throws DonezoException {
        assertCheck(userInput, itemList);

        int taskNdx = Integer.parseInt(userInput.split(" ")[1]) - 1;

        if (taskNdx >= itemList.getSizeItemList()) {
            throw new DonezoException(
                    "Sorry boss, that item does not exist. Try using 'list' to see the index of the item again!");
        }

        Task affectedTask = (Task) itemList.getItem(taskNdx);
        affectedTask.setDone(false);
        ui.unmarkTaskComplete(affectedTask);

        try {
            taskStorage.deleteFromFile(taskStorage.getFilePath(), (TaskList) itemList);
        } catch (IOException e) {
            ui.printGenericErrorMsg();
        }
    }
    
}
