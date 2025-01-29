public class ListCommand extends Command {
    @Override
    public void executeCommand(String userInput, TaskList taskList) {
        System.out.print(ui.printTaskList(taskList));
    }
    
}
