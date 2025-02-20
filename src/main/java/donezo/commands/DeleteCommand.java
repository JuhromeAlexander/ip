package donezo.commands;

import java.io.IOException;

import donezo.lists.ItemList;
import donezo.exceptions.DonezoException;
import donezo.lists.NoteList;
import donezo.lists.TaskList;
import donezo.notes.Note;
import donezo.tasks.Task;


/**
 * Represents a Delete command that can be executed within the application.
 * This class serves as a base for specific command implementations,
 */
public class DeleteCommand extends Command {

    /**
     * Executes the "delete" command by removing a specified task from the task list
     * and updating the task list and file taskStorage accordingly.
     * Throws an exception if the task index provided is invalid.
     *
     * @param userInput the full command input from the user, including the task index to be deleted
     * @param itemList  the task list containing the tasks to be managed
     * @throws DonezoException if the task index is invalid or if an error occurs while updating the taskStorage file
     */
    @Override
    public void executeCommand(String userInput, ItemList itemList) throws DonezoException {
        assertCheck(userInput, itemList);

        String deleteMode = getDeleteMode(userInput);

        int itemNdx = Integer.parseInt(userInput.split(" ")[3]) - 1;
        if (itemNdx > itemList.getSizeItemList() || itemNdx < 0) {
            throw new DonezoException(
                    "Sorry boss, that item does not exist. Try using 'list' to see the index of the item again!");
        }
        deleteHelper(deleteMode, itemList, itemNdx);
    }

    public String getDeleteMode(String userInput) throws DonezoException {
        String[] tokens = userInput.trim().split("\\s+");
        String deleteMode = "";
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equalsIgnoreCase("/m") || tokens[i].equalsIgnoreCase("/mode")) {
                if (i + 1 < tokens.length) {
                    deleteMode = tokens[i + 1].toLowerCase();
                } else {
                    throw new DonezoException(
                            "Hey boss, I think you're forgetting the mode this command is for. Add it in!");
                }
                break;
            }
        }

        if (tokens.length < 4) {
            throw new DonezoException(
                    "Hey boss, you forgot the index of the item you want to delete!");
        }

        return deleteMode;
    }

    private void deleteHelper(String deleteMode, ItemList itemList, int itemNdx) throws DonezoException {
        switch (deleteMode.toLowerCase()) {
        case "tasks":
            ui.printDeleteTask((Task) itemList.getItem(itemNdx));
            itemList.removeItem(itemNdx);
            try {
                taskStorage.deleteFromFile(taskStorage.getFilePath(), (TaskList) itemList);
            } catch (IOException e) {
                throw new DonezoException("Whoops unable to delete file!");
            }
            break;
        case "notes":
            ui.printDeleteNote((Note) itemList.getItem(itemNdx));
            itemList.removeItem(itemNdx);
            try {
                noteStorage.deleteFromFile(noteStorage.getFilePath(), (NoteList) itemList);
            } catch (IOException e) {
                throw new DonezoException("Whoops unable to delete file!");
            }
            break;
        default:
            throw new DonezoException("Sorry boss, that deletion mode does not exist. You entered the following mode: "
                    + deleteMode);
        }
    }
    
}
