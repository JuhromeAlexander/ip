public abstract  class Command {
    protected UI ui = new UI();
    protected Storage storage = new Storage("tasks.txt");

    public abstract void executeCommand(String userInput, TaskList taskList);
}
