package donezo.parser;

import donezo.Storage;
import donezo.TaskList;
import donezo.exceptions.DonezoException;
import donezo.tasks.Task;
import donezo.tasks.Todo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserStorageTest {

    @Test
    public void parseTodo_validMarkedTodo_success() throws DonezoException {
        Storage storage = new Storage("dummyFile.txt");

        String input = "[T][X] testTodo IntelliJ";
        ParserStorage.parseToDo(input, storage);

        TaskList taskList = storage.getTaskList();
        ArrayList<Task> tasks = taskList.getTaskList();

        assertEquals(1, tasks.size());
        assertInstanceOf(Todo.class, tasks.get(0));

        Todo todo = (Todo) tasks.get(0);
        assertEquals("testTodo IntelliJ", todo.getDescription());
        assertTrue(todo.getDoneStatus());
    }

}
