package donezo.commands;

import donezo.lists.ItemList;

/**
 * Represents a List command that can be executed within the application.
 * This class serves as a base for specific command implementations,
 */
public class ListCommand extends Command {
    /**
     * Executes the "list" command by printing all the tasks in the task list.
     *
     * @param userInput the full command input from the user
     * @param itemList  the task list containing the tasks to be printed
     */
    @Override
    public void executeCommand(String userInput, ItemList itemList) {
        assertCheck(userInput, itemList);

        ui.printTaskList(itemList);
    }
    
}
