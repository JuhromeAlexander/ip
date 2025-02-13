package donezo.parser;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import donezo.storage.TaskStorage;
import donezo.TaskList;
import donezo.exceptions.DonezoException;
import donezo.tasks.Task;
import donezo.tasks.Todo;

public class ParserTaskStorageTest {

    @Test
    public void parseTodo_validMarkedTodo_success() throws DonezoException {
        TaskStorage taskStorage = new TaskStorage("dummyFile.txt");

        String input = "[T][X] testTodo IntelliJ";
        ParserTaskStorage.parseToDo(input, taskStorage);

        TaskList taskList = taskStorage.getTaskList();
        ArrayList<Task> tasks = taskList.getTasks();

        assertEquals(1, tasks.size());
        assertInstanceOf(Todo.class, tasks.get(0));

        Todo todo = (Todo) tasks.get(0);
        assertEquals("testTodo IntelliJ", todo.getDescription());
        assertTrue(todo.getDoneStatus());
    }

}
