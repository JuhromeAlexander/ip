import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Donezo {

    String logo = " ______   _______  _        _______  _______  _______ \n" +
            "(  __  \\ (  ___  )( (    /|(  ____ \\/ ___   )(  ___  )\n" +
            "| (  \\  )| (   ) ||  \\  ( || (    \\/\\/   )  || (   ) |\n" +
            "| |   ) || |   | ||   \\ | || (__        /   )| |   | |\n" +
            "| |   | || |   | || (\\ \\) ||  __)      /   / | |   | |\n" +
            "| |   ) || |   | || | \\   || (        /   /  | |   | |\n" +
            "| (__/  )| (___) || )  \\  || (____/\\ /   (_/\\| (___) |\n" +
            "(______/ (_______)|/    )_)(_______/(_______/(_______)\n";

    ArrayList<Task> tasksAL;
    Storage storage;

    public static void main(String[] args) {
        Donezo donezo = new Donezo();
        try {
            donezo.run();
        } catch (DonezoException e) {
            System.out.println(e);
        }
    }

    private void run() throws DonezoException {
        Scanner scanner = new Scanner(System.in);
        storage = new Storage("tasks.txt");
        tasksAL = storage.loadFromFile();
        int numTasks = tasksAL.size();
        System.out.println("Hello from \n" + this.logo + "\n" + "What Can I do for you?");

        

        String inputString = scanner.nextLine();
        
        while (!inputString.equals("bye")) {
            if (inputString.equals("list")) {
                for (int i = 0; i < tasksAL.size(); i++) {
                    int indexNum = i + 1;
                    System.out.println(indexNum + ". " + tasksAL.get(i).toString());
                }
                inputString = scanner.nextLine();
                continue;
            }

            if (inputString.contains("mark") || inputString.contains("unmark")) {
                updateTaskStatus(inputString, tasksAL, storage);
                inputString = scanner.nextLine();
                continue;
            }

            String taskType = inputString.split(" ")[0];
            try {
                updateTaskList(taskType, inputString, tasksAL);
                if (taskType.equals("delete")) {
                    numTasks--;
                } else {
                    numTasks++;
                }
                System.out.println("Now you have " + numTasks + " tasks in your list.");
            } catch (DonezoException e) {
                System.out.println(e.getMessage());
            }
            inputString = scanner.nextLine();
        }

        System.out.println("Peace out. Hope to see you again soon!\n");
        scanner.close();
    }

    private void updateTaskStatus(String userInput, ArrayList<Task> taskList, Storage storage) {
        String[] userInputArr = userInput.split(" ");
        int taskNdx = Integer.parseInt(userInputArr[1]) - 1;
        Task affectedTask = taskList.get(taskNdx);

        if (userInputArr[0].equals("mark")) {
            affectedTask.setDone(true);
            System.out.println("Good. This task is now complete");
        } else {
            affectedTask.setDone(false);
            System.out.println("Really? You need to finish this soon. Marked as Undone");
        }

        try {
            storage.deleteFromFile(storage.getFilePath(), taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
        System.out.println(affectedTask.toString());
    }

    private void updateTaskList(String taskType, String userInput, ArrayList<Task> taskList) throws DonezoException {
        switch (taskType) {
            case "deadline":
                if (!userInput.contains("/by")) {
                    throw new DonezoException("Hey boss, the '/by' argument ain't here. Add it in!");
                }
                String deadlineDescription = userInput.split("/")[0].replace("deadline ", "").trim();
                if (deadlineDescription.isBlank()) {
                    throw new DonezoException(
                            "Hey boss, I think you're forgetting the description this deadline is for. Add it in!");
                }
                String deadlineArgs = userInput.split("/")[1].replace("by", "").trim();
                if (deadlineArgs.isBlank()) {
                    throw new DonezoException("Hey boss, I think you're forgetting the actual deadline. Add it in!");
                }
                Deadline deadlineTask = new Deadline(deadlineDescription, deadlineArgs);
                taskList.add(deadlineTask);
                System.out.println("Got it. This task has been added to your list:\n" + deadlineTask.toString());
                try {
                    storage.writeToFile(storage.getFilePath(), deadlineTask.toString());
                } catch (IOException e) {
                    System.out.println("Unable to save task to file!");
                }
                break;

            case "todo":
                String todoDesc = userInput.substring(5).trim();

                if (todoDesc.isBlank()) {
                    throw new DonezoException("Hey boss, the description for this task can't be empty!");
                }

                Todo todoTask = new Todo(userInput.substring(5).trim());
                taskList.add(todoTask);
                System.out.println("Got it. This task has been added to your list:\n" + todoTask.toString());
                break;

            case "event":
                if (!userInput.contains("/from")) {
                    throw new DonezoException("Hey boss, the '/from' argument ain't here. Add it in!");
                }
                if (!userInput.contains("/to")) {
                    throw new DonezoException("Hey boss, the '/to' argument ain't here. Add it in!");
                }

                String eventDescription = userInput.split("/")[0].replace("event ", "").trim();
                if (eventDescription.isBlank()) {
                    throw new DonezoException(
                            "Hey boss, I think you're forgetting the description this deadline is for. Add it in!");
                }
                String eventFromArgs = userInput.split("/")[1].replace("from", "").trim();
                if (eventFromArgs.isBlank()) {
                    throw new DonezoException("Hey boss, I ain't no mind reader, add the content for the /from field.");
                }
                String eventToArgs = userInput.split("/")[2].replace("to", "").trim();
                if (eventToArgs.isBlank()) {
                    throw new DonezoException("Hey boss, I ain't no mind reader, add the content for the /to field.");
                }

                Event eventTask = new Event(eventDescription, eventFromArgs, eventToArgs);
                taskList.add(eventTask);
                System.out.println("Got it. This task has been added to your list:\n" + eventTask.toString());
                break;

            case "delete":
                int taskNdx = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (taskNdx > taskList.size()) {
                    throw new DonezoException(
                            "Sorry boss, that task does not exist. Try using 'list' to see the index of the task again!");
                }
                System.out.println(
                        "Aight boss, I have removed the following task for you:\n" + taskList.get(taskNdx).toString());
                taskList.remove(taskNdx);
                try {
                    storage.deleteFromFile(storage.getFilePath(), taskList);
                } catch (IOException e) {
                    throw new DonezoException("Whoops unable to delete file!");
                }
                break;
            default:
                throw new DonezoException("Sorry boss, can't help you there. Try another command!");
        }
    }

}
