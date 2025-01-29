package donezo.parser;

import donezo.Storage;

public class Parser {
    public static void parseStorageLine(String lineToParse, Storage storage) {
        if (lineToParse.contains("[D]")) {
            ParserStorage.parseDeadline(lineToParse, storage);
        } else if (lineToParse.contains("[T]")) {
            ParserStorage.parseToDo(lineToParse, storage);
        } else if (lineToParse.contains("[E]")) {
            ParserStorage.parseEvent(lineToParse, storage);
        }
    }

    public String parseCommand(String userInput) {
        String taskType = userInput.split(" ")[0];
        return taskType;
    }
}
