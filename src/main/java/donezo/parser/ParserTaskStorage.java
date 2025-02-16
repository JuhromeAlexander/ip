package donezo.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import donezo.storage.TaskStorage;
import donezo.tasks.Deadline;
import donezo.tasks.Event;
import donezo.tasks.Todo;

/**
 * The ParserTaskStorage class is responsible for parsing task-related input strings
 * from taskStorage files and converting them into corresponding Task objects.
 * It handles Deadline, Todo, and Event tasks, extracting relevant details such as
 * descriptions, completion statuses, and timestamps, and storing them appropriately.
 */
public class ParserTaskStorage {

    /**
     * Parses a line of input containing a "deadline" task, extracts the description,
     * deadline date and time, and completion status from the line, creates a Deadline
     * object, and adds it to the specified taskStorage.
     *
     * @param lineToParse The string containing the serialized representation of a
     *                    "deadline" task, including its description, deadline time,
     *                    and completion status.
     * @param taskStorage The taskStorage object to which the newly created "deadline" task
     *                will be added.
     */
    public static void parseDeadline(String lineToParse, TaskStorage taskStorage) {
        int ndxDescriptionStart = lineToParse.indexOf("] ") + 2;
        int ndxDescriptionEnd = lineToParse.indexOf(" (by: ");
        assert ndxDescriptionStart > 0;
        assert ndxDescriptionEnd > 0;
        String description = lineToParse.substring(ndxDescriptionStart, ndxDescriptionEnd);

        int ndxByStart = lineToParse.indexOf("(by: ") + 5;
        int ndxByEnd = lineToParse.lastIndexOf(')');
        assert ndxByStart > 0;
        assert ndxByEnd > 0;
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

        taskStorage.addTask(deadlineTask);
    }

    /**
     * Parses a line of input containing a "todo" task, extracts the description
     * and completion status from the line, creates a Todo object, and adds it to
     * the specified taskStorage.
     *
     * @param lineToParse The string containing the serialized representation of
     *                    a "todo" task, including its description and completion status.
     * @param taskStorage The taskStorage object to which the newly created "todo" task will be added.
     */
    public static void parseToDo(String lineToParse, TaskStorage taskStorage) {
        int ndxDescriptionStart = lineToParse.indexOf("] ") + 2;
        assert ndxDescriptionStart > 0;
        String description = lineToParse.substring(ndxDescriptionStart);
        Todo todoTask = new Todo(description);

        if (lineToParse.contains("[X]")) {
            todoTask.setDone(true);
        }

        taskStorage.addTask(todoTask);
    }

    /**
     * Parses a line of input containing an event task, extracts relevant details
     * such as description, start time, end time, and completion status, creates
     * an Event object, and adds it to the provided taskStorage.
     *
     * @param lineToParse The string containing the serialized representation of
     *                    an event task, including its description, time range,
     *                    and completion status.
     * @param taskStorage     The taskStorage object to which the newly created Event
     *                    task will be added.
     */
    public static void parseEvent(String lineToParse, TaskStorage taskStorage) {
        int ndxDescriptionStart = lineToParse.indexOf("] ") + 2;
        int ndxDescriptionEnd = lineToParse.indexOf("(from: ");
        assert ndxDescriptionStart > 0;
        assert ndxDescriptionEnd > 0;
        String description = lineToParse.substring(ndxDescriptionStart, ndxDescriptionEnd).trim();

        int ndxFromStart = lineToParse.indexOf("(from: ") + 7;
        int ndxFromEnd = lineToParse.indexOf(" to: ");
        assert ndxFromStart > 0;
        assert ndxFromEnd > 0;
        String from = lineToParse.substring(ndxFromStart, ndxFromEnd).trim();

        int ndxToStart = lineToParse.indexOf("to: ") + 4;
        int ndxToEnd = lineToParse.lastIndexOf(')');
        assert ndxToStart > 0;
        assert ndxToEnd > 0;
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

        taskStorage.addTask(eventTask);
    }

}
