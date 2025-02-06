package donezo.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event type of task that extends the Task class.
 * This class includes additional fields for the start (from) and end time (to)
 * of the event. These times are stored as LocalDateTime instances and are
 * parsed from and formatted to strings using predefined date/time formatters.
 */
public class Event extends Task {

    protected static final DateTimeFormatter INPUT_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected static final DateTimeFormatter OUTPUT_TIME_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Event object with the specified description, start time, and end time.
     * The start and end times are parsed from strings using predefined date/time formatters.
     * If the end time does not include a date, it defaults to the same date as the start time.
     *
     * @param description The description of the event task.
     * @param from        The start date and time of the event as a string formatted as "d/M/yyyy HHmm".
     * @param to          The end date and time of the event as a string formatted as "d/M/yyyy HHmm".
     *                    If no date is provided, it defaults to the same date as the start time.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, INPUT_TIME_FORMATTER);
        
        if (!to.contains("/")) {
            String fromDate = this.from.toLocalDate().toString();
            this.to = LocalDateTime.parse(fromDate + " " + to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else {
            this.to = LocalDateTime.parse(to, INPUT_TIME_FORMATTER);
        }
    }

    /**
     * Retrieves the start date and time of the event.
     *
     * @return The start date and time as a {@code LocalDateTime} object.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Retrieves the end date and time of the event.
     *
     * @return The end date and time as a {@code LocalDateTime} object.
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Returns the string representation of the Event task. This representation includes
     * the task type identifier "[E]", the status and description from the parent class,
     * and the formatted start ("from") and end ("to") date and time.
     *
     * @return A string representation of the Event task, which consists of its type,
     *         status, description, and formatted start and end date/times.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFrom().format(OUTPUT_TIME_FORMATTER)
                + " to: " + getTo().format(OUTPUT_TIME_FORMATTER) + ")";
    }
}
