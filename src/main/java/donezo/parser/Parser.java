package donezo.parser;

import donezo.Storage;

public class Parser {
    /**
     * Parses a line of input from a storage file and delegates the parsing
     * to the appropriate parser method based on the type of task encoded
     * in the line.
     *
     * @param lineToParse The line to be parsed, containing task details and type.
     * @param storage The storage object to which the parsed task will be added.
     */
    public static void parseStorageLine(String lineToParse, Storage storage) {
        if (lineToParse.contains("[D]")) {
            ParserStorage.parseDeadline(lineToParse, storage);
        } else if (lineToParse.contains("[T]")) {
            ParserStorage.parseToDo(lineToParse, storage);
        } else if (lineToParse.contains("[E]")) {
            ParserStorage.parseEvent(lineToParse, storage);
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
