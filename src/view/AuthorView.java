package view;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import controller.AuthorController;
import model.AuthorModel;

public class AuthorView {
    private final TextField authorNameField;
    private final Button addAuthorButton;
    private final ListView<AuthorModel> authorListView;
    private final VBox root;

    public AuthorView() {
        authorListView = new ListView<>();
        
        authorNameField = new TextField();
        authorNameField.setPromptText("Enter author name");

        addAuthorButton = new Button("Add Author");

        HBox addAuthorBox = new HBox(authorNameField, addAuthorButton);

        root = new VBox(authorListView, addAuthorBox);
    }

    public TextField getAuthorNameField() {
        return authorNameField;
    }

    public Button getAddAuthorButton() {
        return addAuthorButton;
    }

    public ListView<AuthorModel> getAuthorListView() {
        return authorListView;
    }

    public VBox getRoot() {
        return root;
    }

    public void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}