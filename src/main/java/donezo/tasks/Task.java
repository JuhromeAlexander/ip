//Code Adapted from Partial Solution Provided on the A-Classes Extension
package donezo.tasks;

/**
 * Represents a task with a description and completion status.
 * This class serves as a base class for different types of tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the status icon representing whether the task is completed or not.
     * A completed task is represented by an "X" and an incomplete task by a blank space.
     *
     * @return A string representing the task's completion status: "X" if done, or " " if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Retrieves the description of the task.
     *
     * @return A string containing the task's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the completion status of the task.
     *
     * @return {@code true} if the task is marked as done, {@code false} otherwise.
     */
    public boolean getDoneStatus() {
        return isDone;
    }

    /**
     * Updates the completion status of the task.
     *
     * @param doneStatus The new completion status for the task.
     *                   {@code true} if the task is marked as done, {@code false} otherwise.
     */
    public void setDone(boolean doneStatus) {
        isDone = doneStatus;
    }

    /**
     * Returns a string representation of the task, which includes its completion status
     * (represented by a status icon) and its description.
     *
     * @return A string representation of the task in the format "[status icon] description".
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
