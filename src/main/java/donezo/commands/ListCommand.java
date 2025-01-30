package  donezo.commands;

import donezo.TaskList;

public class ListCommand extends Command {
    @Override
    public void executeCommand(String userInput, TaskList taskList) {
        ui.printTaskList(taskList);
    }
    
}
