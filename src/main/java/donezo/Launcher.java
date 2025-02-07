package donezo;

import javafx.application.Application;

/**
 * The Launcher class serves as the entry point for launching the JavaFX application.
 * It delegates to the Main class by calling {@code Application.launch(Main.class, args)}.
 */
public class Launcher {

    /**
     * The main method that launches the JavaFX application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

