package  donezo.commands;

import donezo.TaskList;
import donezo.exceptions.DonezoException;
import donezo.tasks.Deadline;
import java.io.IOException;

public class DeadlineCommand extends Command {

    @Override
    public void executeCommand(String userInput, TaskList taskList) throws DonezoException {
        if (!userInput.contains("/by")) {
            throw new DonezoException("Hey boss, the '/by' argument ain't here. Add it in!");
        }
        
        String deadlineDescription = 
                userInput.substring(userInput.indexOf("deadline") + 9, userInput.indexOf("/by")).trim();
        if (deadlineDescription.isBlank()) {
            throw new DonezoException(
                    "Hey boss, I think you're forgetting the description this deadline is for. Add it in!");
        }
        
        String deadlineArgs = userInput.substring(userInput.indexOf("/by") + 4).trim();
        if (deadlineArgs.isBlank()) {
            throw new DonezoException("Hey boss, I think you're forgetting the actual deadline. Add it in!");
        }
        
        Deadline deadlineTask = new Deadline(deadlineDescription, deadlineArgs);
        taskList.addTask(deadlineTask);
        
        ui.printAddTask(deadlineTask);
                        
        try {
            storage.writeToFile(storage.getFilePath(), deadlineTask.toString());
        } catch (IOException e) {
            ui.printUnableToSaveTaskFile();
        }
    }
    
}
