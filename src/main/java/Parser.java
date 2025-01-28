
import java.util.ArrayList;

public class Parser {
    private static ArrayList<Task> taskList;
    private static Storage storage;

    public static void parseStorageLine(String lineToParse, Storage storage) {
        if (lineToParse.contains("[D]")) {
            ParserStorage.parseDeadline(lineToParse, storage);
        }
    }
}
