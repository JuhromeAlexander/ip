package donezo.parser;

import donezo.storage.TaskStorage;

/**
 * The Parser class is responsible for interpreting and processing input lines
 * from taskStorage files and user commands
 */
public class Parser {
    /**
     * Parses a line of input from a taskStorage file and delegates the parsing
     * to the appropriate parser method based on the type of task encoded
     * in the line.
     *
     * @param lineToParse The line to be parsed, containing task details and type.
     * @param taskStorage The taskStorage object to which the parsed task will be added.
     */
    public static void parseStorageLine(String lineToParse, TaskStorage taskStorage) {
        if (lineToParse.contains("[D]")) {
            ParserTaskStorage.parseDeadline(lineToParse, taskStorage);
        } else if (lineToParse.contains("[T]")) {
            ParserTaskStorage.parseToDo(lineToParse, taskStorage);
        } else if (lineToParse.contains("[E]")) {
            ParserTaskStorage.parseEvent(lineToParse, taskStorage);
        }
    }

    /**
     * Extracts the command type from the user's input.
     *
     * @param userInput the full command input from the user
     * @return A string representing the type of command (e.g., "list", "mark", "todo").
     */
    public String parseCommand(String userInput) {
        String taskType = userInput.split(" ")[0];
        return taskType;
    }
}
