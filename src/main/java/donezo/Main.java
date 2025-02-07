package donezo;

import donezo.components.MainWindow;
import donezo.ui.GraphicalUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Donezo donezo;

    @Override
    public void start(Stage stage) {
        try {
            donezo = new Donezo(new GraphicalUI());

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