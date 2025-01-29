import java.util.Scanner;

public class UI {
    private final Scanner input;
    private final String logo = " ______   _______  _        _______  _______  _______ \n" +
    "(  __  \\ (  ___  )( (    /|(  ____ \\/ ___   )(  ___  )\n" +
    "| (  \\  )| (   ) ||  \\  ( || (    \\/\\/   )  || (   ) |\n" +
    "| |   ) || |   | ||   \\ | || (__        /   )| |   | |\n" +
    "| |   | || |   | || (\\ \\) ||  __)      /   / | |   | |\n" +
    "| |   ) || |   | || | \\   || (        /   /  | |   | |\n" +
    "| (__/  )| (___) || )  \\  || (____/\\ /   (_/\\| (___) |\n" +
    "(______/ (_______)|/    )_)(_______/(_______/(_______)\n";

    public UI() {
        this.input = new Scanner(System.in);
    }

    public String nextLine() {
        return input.nextLine();
    }

    public void closeInput() {
        input.close();
    }

    public String greetUser() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello from \n");
        stringBuilder.append(logo);
        stringBuilder.append("\nWhat can I do for you?");
        return stringBuilder.toString();
    }

    public String markTaskComplete() {
        String line = "Good. This task is now complete";
        return line;
    }

    public String unmarkTaskComplete() {
        String line = "Really? You need to finish this soon. Marked as Undone";
        return line;
    }


}
