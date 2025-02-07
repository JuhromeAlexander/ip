package donezo.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The DialogBox class represents a custom control for displaying dialog in the GUI.
 * It consists of a label for the text and an image view for the display picture.
 * The dialog box can be flipped to show the image on the opposite side.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image.
     *
     * @param inputString the text to display.
     * @param image the image to display alongside the text.
     */
    public DialogBox(String inputString, Image image) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        dialog.setText(inputString);
        displayPicture.setImage(image);
    }

    /**
     * Flips the dialog box so that the image is on the left and the text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> temp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(temp);
        this.getChildren().setAll(temp);
    }

    /**
     * Creates and returns a dialog box for the user.
     *
     * @param s the text to display.
     * @param i the image to display.
     * @return a DialogBox representing the user's dialog.
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Creates and returns a dialog box for Donezo's response.
     *
     * @param s the text to display.
     * @param i the image to display.
     * @return a DialogBox representing Donezo's dialog (flipped).
     */
    public static DialogBox getDonezoDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }
}

