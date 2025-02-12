package donezo;

import java.util.ArrayList;

import donezo.tasks.Task;

/**
 * Represents a list of tasks.
 * This class provides functionality to add, remove, and retrieve tasks from the list.
 * It also allows for obtaining the total number of tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSizeTaskList() {
        return tasks.size();
    }

    public Task getTask(int ndx) {
        return tasks.get(ndx);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int ndx) {
        tasks.remove(ndx);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
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
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(searchTerm.toLowerCase())) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }

}
