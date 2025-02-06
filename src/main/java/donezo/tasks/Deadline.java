//Code Adapted from Partial Solution Provided on the A-Inheritance Extension
package donezo.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task which extends the Task class.
 * This class includes an additional datetime field representing the deadline for the task.
 * The deadline is stored as a LocalDateTime instance.
 */
public class Deadline extends Task {
    static final DateTimeFormatter INPUT_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    static final DateTimeFormatter OUTPUT_TIME_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");

    protected LocalDateTime by;

    /**
     * Constructs a new Deadline object with the specified description and deadline date/time.
     * The deadline date/time is parsed from a string using the specified input time formatter.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline date and time as a string, formatted as "d/M/yyyy HHmm".
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, INPUT_TIME_FORMATTER);
    }

    /**
     * Retrieves the deadline date and time associated with the task.
     *
     * @return The deadline date and time as a {@code LocalDateTime} object.
     */
    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Returns the string representation of the Deadline task,
     * which includes its type identifier "[D]", the details of the task from the parent class,
     * and the formatted deadline date and time.
     *
     * @return A string representation of the Deadline task, comprising its type,
     *         description, status, and formatted deadline date/time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy().format(OUTPUT_TIME_FORMATTER) + ")";
    }
}
