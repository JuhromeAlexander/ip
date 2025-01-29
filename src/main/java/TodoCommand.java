
import java.io.IOException;

public class TodoCommand extends Command {
    @Override
    public void executeCommand(String userInput, TaskList taskList) throws DonezoException {
        String todoDesc = userInput.substring(5).trim();    
        if (todoDesc.isBlank()) {
            throw new DonezoException("Hey boss, the description for this task can't be empty!");
        }

        Todo todoTask = new Todo(userInput.substring(5).trim());
        taskList.addTask(todoTask);
        ui.printAddTask(todoTask);

        try {
            storage.writeToFile(storage.getFilePath(), todoTask.toString());
        } catch (IOException e) {
            ui.printUnableToSaveTaskFile();
        }
    }
}
