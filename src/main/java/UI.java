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

    public String sayBye() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Peace out. Hope to see you again soon!\n");
        return stringBuilder.toString();
    }

    public void markTaskComplete() {
        System.out.println("Good. This task is now complete");
    }

    public void unmarkTaskComplete() {
       System.out.println("Really? You need to finish this soon. Marked as Undone");
    }

    public String printTaskList(TaskList taskList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < taskList.getSizeTaskList(); i++) {
            int indexNum = i + 1;
            stringBuilder.append(indexNum);
            stringBuilder.append(". ");
            stringBuilder.append(taskList.getTask(i).toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void genericErrorMsg() {
        System.out.println("Oooops! Something went wrong!");
    }
}
