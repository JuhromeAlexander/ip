package donezo.ui;

import donezo.lists.ItemList;
import donezo.lists.NoteList;
import donezo.lists.TaskList;
import donezo.exceptions.DonezoException;
import donezo.notes.Note;
import donezo.tasks.Task;

public class GraphicalUI implements UI {

    private StringBuilder outputBuffer = new StringBuilder();

    @Override
    public String nextLine() {
        return "";
    }

    @Override
    public void closeInput() {

    }

    /**
     * Generates a greeting message for the user.
     *
     * @return A greeting message as a String.
     */
    @Override
    public String greetUser() {
        outputBuffer.append("Hello from \n");
        outputBuffer.append("Ya Boi Donezo\n");
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
    public void printNumNotes(int numNotes) {
        outputBuffer.append("Now you have ");
        outputBuffer.append(numNotes);
        outputBuffer.append(" notes in your list.\n");
    }

    @Override
    public void printDeleteTask(Task task) {
        outputBuffer.append("Aight boss, I have removed the following task for you:\n");
        outputBuffer.append(task.toString());
    }

    @Override
    public void printDeleteNote(Note note) {
        outputBuffer.append("Aight boss, I have removed the following note for you:\n");
        outputBuffer.append(note.toString());
    }

    @Override
    public void printAddTask(Task task) {
        outputBuffer.append("Got it. This task has been added to your list:\n");
        outputBuffer.append(task.toString());
        outputBuffer.append("\n");
    }

    @Override
    public void printAddNote(Note note) {
        outputBuffer.append("Got it. This note has been added to your list:\n");
        outputBuffer.append(note.toString());
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
     * @param itemList The ItemList object containing the tasks to be printed.
     */
    @Override
    public void printTaskList(ItemList itemList) {
        TaskList taskList = (TaskList) itemList;
        for (int i = 0; i < taskList.getSizeTaskList(); i++) {
            int ndxNum = i + 1;
            outputBuffer.append(ndxNum);
            outputBuffer.append(". ");
            outputBuffer.append(taskList.getTask(i).toString());
            outputBuffer.append("\n");
        }
    }

    @Override
    public void printNoteList(ItemList itemList) {
        NoteList noteList = (NoteList) itemList;
        for (int i = 0; i < noteList.getSizeNoteList(); i++) {
            int ndxNum = i + 1;
            outputBuffer.append(ndxNum);
            outputBuffer.append(". ");
            outputBuffer.append(noteList.getNote(i).toString());
            outputBuffer.append("\n");
        }
    }

    @Override
    public void printGenericErrorMsg() {
        outputBuffer.append("Oooops! Something went wrong!");
    }

    @Override
    public void printEmpty() {
        outputBuffer.append("Hey boss, there are no items in your list.");
    }

    public String getAndClearOutputBuffer() {
        String output = outputBuffer.toString();
        outputBuffer.setLength(0);
        return output;
    }
}
