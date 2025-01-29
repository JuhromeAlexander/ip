package  donezo.commands;

import donezo.TaskList;
import donezo.tasks.Task;
import java.io.IOException;

public class MarkCommand extends Command {

    @Override
    public void executeCommand(String userInput, TaskList taskList) {
        int taskNdx = Integer.parseInt(userInput.split(" ")[1]) - 1;
        Task affectedTask = taskList.getTask(taskNdx);
        affectedTask.setDone(true);
        ui.markTaskComplete();
        System.out.println(affectedTask.toString());

        try {
            storage.deleteFromFile(storage.getFilePath(), taskList);
        } catch (IOException e) {
            ui.genericErrorMsg();
        }
    }
    
}
