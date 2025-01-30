package  donezo.commands;

import donezo.TaskList;
import donezo.tasks.Task;
import java.io.IOException;

public class UnmarkCommand extends Command{

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
