public class Parser {
    public static void parseStorageLine(String lineToParse, Storage storage) {
        if (lineToParse.contains("[D]")) {
            ParserStorage.parseDeadline(lineToParse, storage);
        }
    }
}
