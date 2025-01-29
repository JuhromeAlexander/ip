//Code Adapted from Partial Solution Provided on the A-Inheritance Extension
package donezo.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime by;

    protected static final DateTimeFormatter INPUT_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected static final DateTimeFormatter OUTPUT_TIME_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, INPUT_TIME_FORMATTER);
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy().format(OUTPUT_TIME_FORMATTER) + ")";
    }
}
