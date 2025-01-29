
import java.io.IOException;

public class EventCommand extends Command {

    @Override
    public void executeCommand(String userInput, TaskList taskList) throws DonezoException {
        if (!userInput.contains("/from")) {
            throw new DonezoException("Hey boss, the '/from' argument ain't here. Add it in!");
        }
        if (!userInput.contains("/to")) {
            throw new DonezoException("Hey boss, the '/to' argument ain't here. Add it in!");
        }
                        
        String eventDescription = 
                userInput.substring(userInput.indexOf("event") + 6, userInput.indexOf("/from")).trim();
        if (eventDescription.isBlank()) {
            throw new DonezoException(
                    "Hey boss, I think you're forgetting the description this deadline is for. Add it in!");
        }
        
        String eventFromArgs = 
                userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to")).trim();
        if (eventFromArgs.isBlank()) {
            throw new DonezoException("Hey boss, I ain't no mind reader, add the content for the /from field.");
        }
        
        String eventToArgs = userInput.substring(userInput.indexOf("/to") + 4).trim();
        if (eventToArgs.isBlank()) {
            throw new DonezoException("Hey boss, I ain't no mind reader, add the content for the /to field.");
        }
        
        Event eventTask = new Event(eventDescription, eventFromArgs, eventToArgs);
        taskList.addTask(eventTask);
        ui.printAddTask(eventTask);
        try {
            storage.writeToFile(storage.getFilePath(), eventTask.toString());
        } catch (IOException e) {
            ui.printUnableToSaveTaskFile();
        }
    }
    
}
