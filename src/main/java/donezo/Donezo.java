package donezo;

import donezo.commands.*;
import donezo.exceptions.DonezoException;
import donezo.parser.Parser;

public class Donezo {
    public static void main(String[] args) {
        Donezo donezo = new Donezo();
        try {
            donezo.run();
        } catch (DonezoException e) {
            System.out.println(e);
        }
    }

    private void run() throws DonezoException {
        UI ui = new UI();
        Storage storageActual = new Storage("tasks.txt");
        TaskList taskListActual = storageActual.loadFromFile();
        Parser parser = new Parser();

        int numTasks = taskListActual.getSizeTaskList();

        System.out.println(ui.greetUser());
        String userInput = ui.nextLine();
        
        while (!userInput.equals("bye")) {
            String taskType = parser.parseCommand(userInput);
            switch (taskType.toLowerCase()) {
            case "list":
                ListCommand listCommand = new ListCommand();
                listCommand.executeCommand(userInput, taskListActual);
                userInput = ui.nextLine();
                break;
            
            case "mark":
                MarkCommand markCommand = new MarkCommand();
                markCommand.executeCommand(userInput, taskListActual);
                userInput = ui.nextLine();
                break;
            
            case "unmark":
                UnmarkCommand unmarkCommand = new UnmarkCommand();
                unmarkCommand.executeCommand(userInput, taskListActual);
                userInput = ui.nextLine();
                break;

            case "delete":
                DeleteCommand deleteCommand = new DeleteCommand();
                try {
                    deleteCommand.executeCommand(userInput, taskListActual);
                    numTasks--;
                    ui.printNumTasks(numTasks);
                } catch (DonezoException e) {
                    ui.printDonezoExceptionMessage(e);
                }
                userInput = ui.nextLine();
                break;
            
            case "deadline":
                DeadlineCommand deadlineCommand = new DeadlineCommand();
                try {
                    deadlineCommand.executeCommand(userInput, taskListActual);
                    numTasks++;
                    ui.printNumTasks(numTasks);
                } catch (DonezoException e) {
                    ui.printDonezoExceptionMessage(e);
                }
                userInput = ui.nextLine();
                break;
            
            case "event":
                EventCommand eventCommand = new EventCommand();
                try {
                    eventCommand.executeCommand(userInput, taskListActual);
                    numTasks++;
                    ui.printNumTasks(numTasks);
                } catch (DonezoException e) {
                    ui.printDonezoExceptionMessage(e);
                }
                userInput = ui.nextLine();
                break;

            case "todo":
                TodoCommand todoCommand = new TodoCommand();
                try {
                    todoCommand.executeCommand(userInput, taskListActual);
                    numTasks++;
                    ui.printNumTasks(numTasks);
                } catch (DonezoException e) {
                    ui.printDonezoExceptionMessage(e);
                }
                userInput = ui.nextLine();
                break;
            
            default:
                ui.printTryCommandAgain();
                userInput = ui.nextLine();
            }
        }
        System.out.println(ui.sayBye());
        ui.closeInput();

    }
}
