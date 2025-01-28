
public class ParserStorage {
    
    public static void parseDeadline(String lineToParse, Storage storage) {
        int ndxDescriptionStart = lineToParse.indexOf("] ") + 2;
        int ndxDescriptionEnd = lineToParse.indexOf(" (by: ");
        String description = lineToParse.substring(ndxDescriptionStart, ndxDescriptionEnd);

        int ndxByStart = lineToParse.indexOf("(by: ") + 5;
        int ndxByEnd = lineToParse.lastIndexOf(')');
        String by = lineToParse.substring(ndxByStart, ndxByEnd);
        
        Deadline deadlineTask = new Deadline(description, by);

        if (lineToParse.contains("[X]")) {
            deadlineTask.setDone(true);
        }

        storage.addTask(deadlineTask);
    }

    public static void parseToDo(String lineToParse, Storage storage) {
        int ndxDescriptionStart = lineToParse.indexOf("] ") + 2;
        String description = lineToParse.substring(ndxDescriptionStart);
        Todo todoTask = new Todo(description);

        if (lineToParse.contains("[X]")) {   
            todoTask.setDone(true);
        }
        
        storage.addTask(todoTask);
    }

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

        Event eventTask = new Event(description, from, to);

        if (lineToParse.contains("[X]")) {
            eventTask.setDone(true);
        }

        storage.addTask(eventTask);
    }

}
