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

    ArrayList<String> tasksAL;
    public static void main(String[] args) {
        Donezo donezo = new Donezo();
        donezo.run();
    }
    
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello from \n" + this.logo + "\n" + "What Can I do for you?");
        
        String inputString = scanner.nextLine();
        tasksAL = new ArrayList<String>();

        while(!inputString.equals("bye")) {
            if (inputString.equals("list")) {
                for (int i = 0; i < tasksAL.size(); i++) {
                    int indexNum = i + 1;
                    System.out.println(indexNum + ". " + tasksAL.get(i).toString());
                }
                inputString = scanner.nextLine();
                continue;
            }
            tasksAL.add(inputString);
            System.out.println("added: " + inputString);
            inputString = scanner.nextLine();
        }

        System.out.println("Peace out. Hope to see you again soon!\n");
        scanner.close();
    }
}
