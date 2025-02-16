package donezo.lists;

import java.util.ArrayList;

import donezo.tasks.Task;

/**
 * Represents a list of tasks.
 * This class provides functionality to add, remove, and retrieve tasks from the list.
 * It also allows for obtaining the total number of tasks in the list.
 */
public class TaskList extends ItemList<Task> {
    public TaskList(ArrayList<Task> tasks) {
        super(tasks);
    }

    public TaskList() {
        super();
    }

    @Override
    protected String getSearchField(Task item) {
        return item.getDescription();
    }

    @Override
    protected ItemList<Task> createEmptyList() {
        return new TaskList();
    }

    public ArrayList<Task> getTasks() {
        return getItems();
    }

    public int getSizeTaskList() {
        return getSizeItemList();
    }

    public Task getTask(int ndx) {
        return getItem(ndx);
    }

    public void addTask(Task task) {
        addItem(task);
    }

    public void removeTask(int ndx) {
        removeItem(ndx);
    }

    public boolean isEmpty() {
        return isItemListEmpty();
    }

    /**
     * Searches for tasks in the current task list that contain a specific search term
     * (case-insensitive) in their description and returns a new TaskList containing
     * the matching tasks.
     *
     * @param searchTerm The term to search for within the task descriptions.
     *                   This search is case-insensitive, meaning "Task" and "task"
     *                   would be treated as equivalent.
     * @return A TaskList containing all tasks that match the specified search term.
     *         If no tasks match, an empty TaskList is returned.
     */
    public TaskList findMatchingTasks(String searchTerm) {
        return (TaskList) findMatchingItems(searchTerm);
    }

}
