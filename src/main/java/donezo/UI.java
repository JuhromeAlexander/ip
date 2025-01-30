package donezo;

import donezo.exceptions.DonezoException;
import donezo.tasks.Task;
import java.util.Scanner;

/**
 * A utility class for managing interaction between the program and the user.
 * Provides methods for taking input, displaying messages, and interacting with tasks.
 */
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

    public void printNumTasks(int numTasks) {
        System.out.println("Now you have " + numTasks + " tasks in your list.");
    }

    public void printDeleteTask(Task task) {
        System.out.println("Aight boss, I have removed the following task for you:\n" + task.toString());
    }

    public void printAddTask(Task task) {
        System.out.println("Got it. This task has been added to your list:\n" + task.toString());
    }

    public void printDonezoExceptionMessage(DonezoException e) {
        System.out.println(e.getMessage());
    }

    public void printUnableToSaveTaskFile() {
        System.out.println("Unable to save task to file!");
    }

    public void printTryCommandAgain() {
        System.out.println("Sorry boss, can't help you there. Try another command!");
    }

    /**
     * Constructs a formatted string representing all tasks in the specified TaskList.
     * Each task in the list is prefixed with its index (starting from 1) and includes
     * its string representation as provided by the Task class's toString method, with
     * individual tasks separated by newline characters.
     *
     * @param taskList The TaskList containing the tasks to be printed.
     * @return A string representation of all tasks in the TaskList, where each task is
     *         prefixed by its index and separated by a newline.
     */
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
