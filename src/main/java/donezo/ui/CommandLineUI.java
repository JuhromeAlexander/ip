package donezo.ui;

import java.util.Scanner;

import donezo.lists.ItemList;
import donezo.lists.NoteList;
import donezo.lists.TaskList;
import donezo.exceptions.DonezoException;
import donezo.notes.Note;
import donezo.tasks.Task;

/**
 * The CommandLineUI class handles user interactions, including input and output operations.
 * It provides methods for displaying messages and handling user commands.
 */
public class CommandLineUI implements UI {
    private final Scanner input;
    private final String logo = " ______   _______  _        _______  _______  _______ \n"
            + "(  __  \\ (  ___  )( (    /|(  ____ \\/ ___   )(  ___  )\n"
            + "| (  \\  )| (   ) ||  \\  ( || (    \\/\\/   )  || (   ) |\n"
            + "| |   ) || |   | ||   \\ | || (__        /   )| |   | |\n"
            + "| |   | || |   | || (\\ \\) ||  __)      /   / | |   | |\n"
            + "| |   ) || |   | || | \\   || (        /   /  | |   | |\n"
            + "| (__/  )| (___) || )  \\  || (____/\\ /   (_/\\| (___) |\n"
            + "(______/ (_______)|/    )_)(_______/(_______/(_______)\n";

    public CommandLineUI() {
        this.input = new Scanner(System.in);
    }

    public String nextLine() {
        return input.nextLine();
    }

    public void closeInput() {
        input.close();
    }

    /**
     * Generates a greeting message for the user.
     *
     * @return A greeting message as a String.
     */
    @Override
    public String greetUser() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello from \n");
        stringBuilder.append(logo);
        stringBuilder.append("\nWhat can I do for you?");
        return stringBuilder.toString();
    }

    /**
     * Generates a farewell message for the user.
     *
     * @return A farewell message as a String.
     */
    @Override
    public String sayBye() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Peace out. Hope to see you again soon!\n");
        return stringBuilder.toString();
    }

    @Override
    public void markTaskComplete(Task task) {
        System.out.println("Good. This task is now complete:\n" + task.toString());
    }

    @Override
    public void unmarkTaskComplete(Task task) {
        System.out.println("Really? You need to finish this soon. Marked this task as undone:\n" + task.toString());
    }

    @Override
    public void printNumTasks(int numTasks) {
        System.out.println("Now you have " + numTasks + " tasks in your list.");
    }

    @Override
    public void printNumNotes(int numNotes) {
        System.out.println("Now you have " + numNotes + " notes in your list.");
    }

    @Override
    public void printDeleteTask(Task task) {
        System.out.println("Aight boss, I have removed the following task for you:\n" + task.toString());
    }

    @Override
    public void printDeleteNote(Note note) {
        System.out.println("Aight boss, I have removed the following task for you:\n" + note.toString());
    }

    @Override
    public void printAddTask(Task task) {
        System.out.println("Got it. This task has been added to your list:\n" + task.toString());
    }

    @Override
    public void printAddNote(Note note) {
        System.out.println("Got it. This note has been added to your list:\n" + note.toString());
    }

    @Override
    public void printDonezoExceptionMessage(DonezoException e) {
        System.out.println(e.getMessage());
    }

    @Override
    public void printUnableToSaveTaskFile() {
        System.out.println("Unable to save task to file!");
    }

    @Override
    public void printTryCommandAgain() {
        System.out.println("Sorry boss, can't help you there. Try another command!");
    }

    @Override
    public void printTaskNotFound() {
        System.out.println("Hey boss, that task doesn't exist in your list! Maybe you misspelled it?");
    }

    @Override
    public void printTaskFound() {
        System.out.println("Hey boss, here are the tasks that matched your search term:");
    }

    /**
     * Prints the list of tasks in the given TaskList object. Each task is displayed
     * with its index in the list, starting from 1, followed by its string representation.
     * This method outputs the entire task list to the standard output.
     *
     * @param itemList The ItemList object containing the tasks to be printed.
     */
    @Override
    public void printTaskList(ItemList itemList) {
        TaskList taskList = (TaskList) itemList;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < taskList.getSizeTaskList(); i++) {
            int indexNum = i + 1;
            stringBuilder.append(indexNum);
            stringBuilder.append(". ");
            stringBuilder.append(taskList.getTask(i).toString());
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }

    @Override
    public void printNoteList(ItemList itemList) {
        NoteList noteList = (NoteList) itemList;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < noteList.getSizeNoteList(); i++) {
            int indexNum = i + 1;
            stringBuilder.append(indexNum);
            stringBuilder.append(". ");
            stringBuilder.append(noteList.getNote(i).toString());
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }

    @Override
    public void printEmpty() {
        System.out.println("Hey boss, there are no items in your list.");
    }

    @Override
    public void printGenericErrorMsg() {
        System.out.println("Oooops! Something went wrong!");
    }
}
