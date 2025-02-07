package donezo.ui;

import donezo.TaskList;
import donezo.exceptions.DonezoException;
import donezo.tasks.Task;

public class GraphicalUI implements UI {
    private final String logo = " ______   _______  _        _______  _______  _______ \n"
            + "(  __  \\ (  ___  )( (    /|(  ____ \\/ ___   )(  ___  )\n"
            + "| (  \\  )| (   ) ||  \\  ( || (    \\/\\/   )  || (   ) |\n"
            + "| |   ) || |   | ||   \\ | || (__        /   )| |   | |\n"
            + "| |   | || |   | || (\\ \\) ||  __)      /   / | |   | |\n"
            + "| |   ) || |   | || | \\   || (        /   /  | |   | |\n"
            + "| (__/  )| (___) || )  \\  || (____/\\ /   (_/\\| (___) |\n"
            + "(______/ (_______)|/    )_)(_______/(_______/(_______)\n";

    private StringBuilder outputBuffer = new StringBuilder();

    /**
     * Generates a greeting message for the user.
     *
     * @return A greeting message as a String.
     */
    @Override
    public String greetUser() {
        outputBuffer.append("Hello from \n");
        outputBuffer.append(logo);
        outputBuffer.append("\nWhat can I do for you?");
        return outputBuffer.toString();
    }

    /**
     * Generates a farewell message for the user.
     *
     * @return A farewell message as a String.
     */
    @Override
    public String sayBye() {
        outputBuffer.append("Peace out. Hope to see you again soon!\n");
        return outputBuffer.toString();
    }

    @Override
    public void markTaskComplete(Task task) {
        outputBuffer.append("Good. This task is now complete:\n");
        outputBuffer.append(task.toString());
    }

    @Override
    public void unmarkTaskComplete(Task task) {
        outputBuffer.append("Really? You need to finish this soon. Marked this task as undone:\n");
        outputBuffer.append(task.toString());
    }

    @Override
    public void printNumTasks(int numTasks) {
        outputBuffer.append("Now you have ");
        outputBuffer.append(numTasks);
        outputBuffer.append(" tasks in your list.\n");
    }

    @Override
    public void printDeleteTask(Task task) {
        outputBuffer.append("Aight boss, I have removed the following task for you:\n");
        outputBuffer.append(task.toString());
    }

    @Override
    public void printAddTask(Task task) {
        outputBuffer.append("Got it. This task has been added to your list:\n");
        outputBuffer.append(task.toString());
    }

    @Override
    public void printDonezoExceptionMessage(DonezoException donezo) {
        outputBuffer.append(donezo.getMessage());
    }

    @Override
    public void printUnableToSaveTaskFile() {
        outputBuffer.append("Unable to save task to file!");
    }

    @Override
    public void printTryCommandAgain() {
        outputBuffer.append("Sorry boss, can't help you there. Try another command!");
    }

    @Override
    public void printTaskNotFound() {
        outputBuffer.append("Hey boss, that task doesn't exist in your list! Maybe you misspelled it?");
    }

    @Override
    public void printTaskFound() {
        outputBuffer.append("Hey boss, here are the tasks that matched your search term:");
    }

    /**
     * Appends the list of tasks in the given TaskList object to the buffer. Each task is displayed
     * with its index in the list, starting from 1, followed by its string representation.
     * This method outputs the entire task list to the output buffer.
     *
     * @param taskList The TaskList object containing the tasks to be printed.
     */
    @Override
    public void printTaskList(TaskList taskList) {
        for (int i = 0; i < taskList.getSizeTaskList(); i++) {
            int ndxNum = i + 1;
            outputBuffer.append(ndxNum);
            outputBuffer.append(". ");
            outputBuffer.append(taskList.getTask(i).toString());
            outputBuffer.append("\n");
        }
    }

    @Override
    public void printGenericErrorMsg() {
        outputBuffer.append("Oooops! Something went wrong!");
    }

    public String getAndClearOutputBuffer() {
        String output = outputBuffer.toString();
        outputBuffer.setLength(0);
        return output;
    }
}
