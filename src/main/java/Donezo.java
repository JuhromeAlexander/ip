import java.util.ArrayList;
import java.util.Scanner;

public class Donezo {

    String logo =
        " ______   _______  _        _______  _______  _______ \n" +
        "(  __  \\ (  ___  )( (    /|(  ____ \\/ ___   )(  ___  )\n" +
        "| (  \\  )| (   ) ||  \\  ( || (    \\/\\/   )  || (   ) |\n" +
        "| |   ) || |   | ||   \\ | || (__        /   )| |   | |\n" +
        "| |   | || |   | || (\\ \\) ||  __)      /   / | |   | |\n" +
        "| |   ) || |   | || | \\   || (        /   /  | |   | |\n" +
        "| (__/  )| (___) || )  \\  || (____/\\ /   (_/\\| (___) |\n" +
        "(______/ (_______)|/    )_)(_______/(_______/(_______)\n";

    ArrayList<Task> tasksAL;
    public static void main(String[] args) {
        Donezo donezo = new Donezo();
        donezo.run();
    }
    
    private void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello from \n" + this.logo + "\n" + "What Can I do for you?");

        int numTasks = 0;
        
        String inputString = scanner.nextLine();
        tasksAL = new ArrayList<Task>();

        while(!inputString.equals("bye")) {
            if (inputString.equals("list")) {
                for (int i = 0; i < tasksAL.size(); i++) {
                    int indexNum = i + 1;
                    System.out.println(indexNum + ". " + tasksAL.get(i).toString());
                }
                inputString = scanner.nextLine();
                continue;
            }

            if (inputString.contains("mark") || inputString.contains("unmark")) {
                updateTaskStatus(inputString, tasksAL);
                inputString = scanner.nextLine();
                continue;
            }

            tasksAL.add(new Task(inputString));
            System.out.println("added: " + tasksAL.get(numTasks).getDescription());
            numTasks++;
            inputString = scanner.nextLine();
        }

        System.out.println("Peace out. Hope to see you again soon!\n");
        scanner.close();
    }

    private void updateTaskStatus(String userInput, ArrayList<Task> taskList) {
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
        System.out.println(affectedTask.toString());
    }
}
