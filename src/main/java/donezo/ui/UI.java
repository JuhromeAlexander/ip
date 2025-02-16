package donezo.ui;

import donezo.lists.ItemList;
import donezo.lists.TaskList;
import donezo.exceptions.DonezoException;
import donezo.notes.Note;
import donezo.tasks.Task;

public interface UI {
    void closeInput();
    String nextLine();
    String greetUser();
    String sayBye();
    void markTaskComplete(Task task);
    void unmarkTaskComplete(Task task);
    void printNumTasks(int numTasks);
    void printNumNotes(int numNotes);
    void printDeleteTask(Task task);
    void printAddTask(Task task);
    void printAddNote(Note note);
    void printDonezoExceptionMessage(DonezoException donezo);
    void printUnableToSaveTaskFile();
    void printTryCommandAgain();
    void printTaskNotFound();
    void printTaskFound();
    void printTaskList(ItemList itemList);
    void printNoteList(ItemList itemList);
    void printGenericErrorMsg();
}
