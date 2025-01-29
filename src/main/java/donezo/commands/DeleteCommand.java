package  donezo.commands;

import donezo.TaskList;
import donezo.exceptions.DonezoException;
import java.io.IOException;

public class DeleteCommand extends Command {
    @Override
    public void executeCommand(String userInput, TaskList taskList) throws DonezoException{
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
