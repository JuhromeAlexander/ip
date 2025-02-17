package donezo;

import donezo.components.MainWindow;
import donezo.ui.GraphicalUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class is the entry point for the JavaFX GUI version of the application.
 * It extends {@code Application} and is launched by the {@code Launcher} class.
 * In the {@code start} method, it initializes the backend (Donezo) with a graphical UI,
 * loads the FXML layout, injects the Donezo instance into the controller, and displays the window.
 */
public class Main extends Application {

    private Donezo donezo;

    @Override
    public void start(Stage stage) {
        try {
            donezo = new Donezo(new GraphicalUI());

            // From JavaFxPart 5 Tutorial - Setting Minimum Width & Height
            stage.setMinHeight(220);
            stage.setMinWidth(420);

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setDonezo(donezo);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}