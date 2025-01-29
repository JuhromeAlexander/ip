import java.io.IOException;

public class UnmarkCommand extends Command{

    @Override
    public void executeCommand(String userInput, TaskList taskList) {
        int taskNdx = Integer.parseInt(userInput.split(" ")[1]) - 1;
        Task affectedTask = taskList.getTask(taskNdx);
        affectedTask.setDone(false);
        ui.unmarkTaskComplete();
        System.out.println(affectedTask.toString());

        try {
            storage.deleteFromFile(storage.getFilePath(), taskList);
        } catch (IOException e) {
            ui.genericErrorMsg();
        }
    }
    
}
