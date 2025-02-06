package donezo.tasks;

/**
 * Represents an Todo type of task that extends the Task class.
 */
public class Todo extends Task {
    
    /**
     * Constructs a new Todo object with the specified description.
     * This constructor initializes a Todo task and marks it as not done,
     * inheriting functionality from the Task class.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     * This representation includes the task type identifier "[T]" followed
     * by the string output of the parent Task's toString method, which includes
     * the task's status and description.
     *
     * @return A string representation of the Todo task, comprising its type indicator
     *         and its inherited details from the Task superclass.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
