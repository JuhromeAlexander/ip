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
    public static void main(String[] args) {
        Donezo donezo = new Donezo();
        donezo.run();
    }
    
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello from \n" + this.logo + "\n" + "What Can I do for you?");
        
        String inputString = scanner.nextLine();

        while(!inputString.equals("bye")) {
            System.out.println(inputString);
            inputString = scanner.nextLine();
        }

        System.out.println("Peace out. Hope to see you again soon!\n");
        scanner.close();
    }
}
