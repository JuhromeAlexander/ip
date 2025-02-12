package donezo.commands;

import java.io.IOException;

import donezo.TaskList;
import donezo.exceptions.DonezoException;
import donezo.tasks.Deadline;

/**
 * Represents a Deadline command that can be executed within the application.
 * This class serves as a base for specific command implementations,
 */
public class DeadlineCommand extends Command {

    /**
     * Executes the "deadline" command by extracting the task description and due date,
     * creating a Deadline task, and adding it to the given task list
     *
     * @param userInput the full command input from the user
     * @param taskList the task list where the deadline task will be added
     * @throws DonezoException if the input is missing the "/by" argument, task description or deadline date
     */
    @Override
    public void executeCommand(String userInput, TaskList taskList) throws DonezoException {
        assertCheck(userInput, taskList);

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
