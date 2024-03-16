package controller;

import filehandling.AuthorDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.AuthorModel;

public class AuthorController {
    private final AuthorDAO authorDAO;
    private final VBox view;

    public AuthorController() {
        this.authorDAO = new AuthorDAO();
        this.view = initialize();
    }

    private VBox initialize() {
        ListView<AuthorModel> authorListView = new ListView<>(authorDAO.getAll());

        TextField authorNameField = new TextField();
        authorNameField.setPromptText("Enter author name");

        Button addAuthorButton = new Button("Add Author");
        addAuthorButton.setOnAction(event -> {
            String authorName = authorNameField.getText();
            if (addAuthor(authorName)) {
                authorNameField.clear();
                authorListView.setItems(authorDAO.getAll());
            }
        });

        HBox addAuthorBox = new HBox(authorNameField, addAuthorButton);

        VBox root = new VBox(authorListView, addAuthorBox);
        return root;
    }

    public VBox getView() {
        return view;
    }

    public boolean addAuthor(String authorName) {
        if (!authorDAO.authorExists(authorName) && !authorName.isEmpty()) {
            AuthorModel authorModel = new AuthorModel(authorName); // Corrected variable name
            if (authorDAO.create(authorModel)) {
                showAlert("Success", "Author added successfully.");
                return true;
            } else {
                showAlert("Error", "Failed to add author.");
            }
        } else {
            showAlert("Error", "Author already exists or is empty.");
        }
        return false;
    }

    // Add other methods as needed for author management

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

