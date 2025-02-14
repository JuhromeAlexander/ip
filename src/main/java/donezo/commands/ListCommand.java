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
        String listType = getListType(userInput);

        switch (listType.toLowerCase()) {
        case "tasks", "notes":
            listHelper(listType, itemList);
            break;
        }

    }

    public String getListType(String userInput) {
        String[] tokens = userInput.trim().split("\\s+");
        String listType = "tasks";
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equalsIgnoreCase("/t") || tokens[i].equalsIgnoreCase("/type")) {
                if (i + 1 < tokens.length) {
                    listType = tokens[i + 1].toLowerCase();
                } else {
                    ui.printGenericErrorMsg();
                    break;
                }
            }
        }

        return listType;
    }

    private void listHelper(String listType, ItemList itemList) {
        if (itemList.isItemListEmpty()) {
            ui.printGenericErrorMsg();
        } else {
            if (listType.equalsIgnoreCase("tasks")) {
                ui.printTaskList(itemList);
            } else {
                ui.printNoteList(itemList);
            }
        }
    }
    
}
