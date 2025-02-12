package donezo.commands;

import donezo.TaskList;
import donezo.exceptions.DonezoException;

/**
 * Represents a Find command that can be executed within the application.
 * This class serves as a base for specific command implementations,
 */
public class FindCommand extends Command {

    /**
     * Executes the Find Command. Takes in an instruction from the user, and splits the
     * instruction into its search parameter to find all tasks that contains the search
     * parameter
     *
     * @param userInput a string representing the input of the user
     * @param taskList the list of tasks that will be used by the command
     */
    @Override
    public void executeCommand(String userInput, TaskList taskList) throws DonezoException {
        assertCheck(userInput, taskList);

        String searchTerm = userInput.substring(4).trim();

        if (searchTerm.isBlank()) {
            throw new DonezoException("Hey boss, can't search if you don't give me a search term!");
        }

        TaskList matchingTasks = taskList.findMatchingTasks(searchTerm);

        if (matchingTasks.isEmpty()) {
            ui.printTaskNotFound();
        } else {
            ui.printTaskFound();
            ui.printTaskList(matchingTasks);
        }
    }
}
