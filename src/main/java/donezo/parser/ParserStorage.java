package donezo.parser;

import donezo.Storage;
import donezo.tasks.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParserStorage {

    /**
     * Parses a line of input containing a "deadline" task, extracts the description,
     * deadline date and time, and completion status from the line, creates a Deadline
     * object, and adds it to the specified storage.
     *
     * @param lineToParse The string containing the serialized representation of a
     *                    "deadline" task, including its description, deadline time,
     *                    and completion status.
     * @param storage The storage object to which the newly created "deadline" task
     *                will be added.
     */
    public static void parseDeadline(String lineToParse, Storage storage) {
        int ndxDescriptionStart = lineToParse.indexOf("] ") + 2;
        int ndxDescriptionEnd = lineToParse.indexOf(" (by: ");
        String description = lineToParse.substring(ndxDescriptionStart, ndxDescriptionEnd);

        int ndxByStart = lineToParse.indexOf("(by: ") + 5;
        int ndxByEnd = lineToParse.lastIndexOf(')');
        String by = lineToParse.substring(ndxByStart, ndxByEnd);

        // DateTimeFormatters for Saved Deadlines in the Task File
        DateTimeFormatter storedFormat = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");
        DateTimeFormatter deadlineConstructorFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        LocalDateTime byDateTime = LocalDateTime.parse(by, storedFormat);
        String formattedBy = byDateTime.format(deadlineConstructorFormat);
        
        Deadline deadlineTask = new Deadline(description, formattedBy);

        if (lineToParse.contains("[X]")) {
            deadlineTask.setDone(true);
        }

        storage.addTask(deadlineTask);
    }

    /**
     * Parses a line of input containing a "todo" task, extracts the description
     * and completion status from the line, creates a Todo object, and adds it to
     * the specified storage.
     *
     * @param lineToParse The string containing the serialized representation of
     *                    a "todo" task, including its description and completion status.
     * @param storage The storage object to which the newly created "todo" task will be added.
     */
    public static void parseToDo(String lineToParse, Storage storage) {
        int ndxDescriptionStart = lineToParse.indexOf("] ") + 2;
        String description = lineToParse.substring(ndxDescriptionStart);
        Todo todoTask = new Todo(description);

        if (lineToParse.contains("[X]")) {   
            todoTask.setDone(true);
        }

        storage.addTask(todoTask);
    }

    /**
     * Parses a line of input containing an event task, extracts relevant details
     * such as description, start time, end time, and completion status, creates
     * an Event object, and adds it to the provided storage.
     *
     * @param lineToParse The string containing the serialized representation of
     *                    an event task, including its description, time range,
     *                    and completion status.
     * @param storage     The storage object to which the newly created Event
     *                    task will be added.
     */
    public static void parseEvent(String lineToParse, Storage storage) {
        int ndxDescriptionStart = lineToParse.indexOf("] ") + 2;
        int ndxDescriptionEnd = lineToParse.indexOf("(from: ");
        String description = lineToParse.substring(ndxDescriptionStart, ndxDescriptionEnd).trim();

        int ndxFromStart = lineToParse.indexOf("(from: ") + 7;
        int ndxFromEnd = lineToParse.indexOf(" to: ");
        String from = lineToParse.substring(ndxFromStart, ndxFromEnd).trim();

        int ndxToStart = lineToParse.indexOf("to: ") + 4;
        int ndxToEnd = lineToParse.lastIndexOf(')');
        String to = lineToParse.substring(ndxToStart, ndxToEnd);

        // DateTimeFormatters for Saved Events in the Task File
        DateTimeFormatter storedFormat = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");
        DateTimeFormatter eventConstructorFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        LocalDateTime fromDateTime = LocalDateTime.parse(from, storedFormat);
        LocalDateTime toDateTime = LocalDateTime.parse(to, storedFormat);

        String formattedFrom = fromDateTime.format(eventConstructorFormat);
        String formattedTo = toDateTime.format(eventConstructorFormat);

        Event eventTask = new Event(description, formattedFrom, formattedTo);

        if (lineToParse.contains("[X]")) {
            eventTask.setDone(true);
        }

        storage.addTask(eventTask);
    }

}
