
public class ParserStorage {
    public static void parseDeadline(String lineToParse, Storage storage) {
        if (lineToParse.contains("[X]")) {
            int ndxDescriptionStart = lineToParse.indexOf("] ") + 2;
            int ndxDescriptionEnd = lineToParse.indexOf(" (by: ");
            String description = lineToParse.substring(ndxDescriptionStart, ndxDescriptionEnd);

            int ndxByStart = lineToParse.indexOf("(by: ") + 5;
            int ndxByEnd = lineToParse.lastIndexOf(')');
            String by = lineToParse.substring(ndxByStart, ndxByEnd);
            
            Deadline deadlineTask = new Deadline(description, by);
            deadlineTask.setDone(true);
            storage.addTask(deadlineTask);
        } else {
            int ndxDescriptionStart = lineToParse.indexOf("] ") + 2;
            int ndxDescriptionEnd = lineToParse.indexOf(" (by: ");
            String description = lineToParse.substring(ndxDescriptionStart, ndxDescriptionEnd);

            int ndxByStart = lineToParse.indexOf("(by: ") + 5;
            int ndxByEnd = lineToParse.lastIndexOf(')');
            String by = lineToParse.substring(ndxByStart, ndxByEnd);
            
            Deadline deadlineTask = new Deadline(description, by);
            storage.addTask(deadlineTask);
        }
    }

}
