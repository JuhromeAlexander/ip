package  donezo.commands;

import donezo.TaskList;

public class ListCommand extends Command {
    /**
     * Executes the "list" command by printing all the tasks in the task list.
     *
     * @param userInput the full command input from the user
     * @param taskList the task list containing the tasks to be printed
     */
    @Override
    public void executeCommand(String userInput, TaskList taskList) {
        ui.printTaskList(taskList);
    }
    
}
