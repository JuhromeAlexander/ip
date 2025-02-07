package donezo.components;

import donezo.Donezo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Donezo donezo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/image1.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/image2.jpg"));

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDonezo(Donezo d) {
        this.donezo = d;
        String greetingMessage = donezo.getGreeting();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(greetingMessage, dukeImage));
    }

    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String response = donezo.getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

}
