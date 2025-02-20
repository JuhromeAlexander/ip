package donezo.components;

import donezo.Donezo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * The MainWindow class serves as the controller for the main GUI window.
 * It handles user interactions, including receiving user input, displaying dialogs,
 * and updating the view.
 */
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

    /**
     * Initializes the MainWindow. Binds the vertical scroll of the scroll pane to the height of the dialog container,
     * ensuring that the latest dialog is visible.
     */
    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Donezo instance (backend) for this controller.
     * Upon setting, it retrieves and displays the greeting message from the backend.
     *
     * @param d the Donezo instance that contains the application logic.
     */
    public void setDonezo(Donezo d) {
        this.donezo = d;
        String greetingMessage = donezo.getGreeting();
        dialogContainer.getChildren().add(DialogBox.getDonezoDialog(greetingMessage, dukeImage));
    }

    /**
     * Handles user input when the user presses Enter or clicks the Send button.
     * It retrieves the user input, gets a response from the backend, creates dialog boxes for both,
     * and adds them to the dialog container. Finally, it clears the user input field.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String response = donezo.getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDonezoDialog(response, dukeImage)
        );
        userInput.clear();
        if (userText.equals("bye")) {
            javafx.application.Platform.exit();
        }
    }

}
