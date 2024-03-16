package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.BookCategory;

public class CategoryView {
    private final ListView<BookCategory> categoryListView;
    private final TextField categoryNameField;
    private final Button addCategoryButton;
    private final VBox root;

    public CategoryView() {
        categoryListView = new ListView<>();
        
        categoryNameField = new TextField();
        categoryNameField.setPromptText("Enter category name");

        addCategoryButton = new Button("Add Category");

        HBox addCategoryBox = new HBox(categoryNameField, addCategoryButton);

        root = new VBox(categoryListView, addCategoryBox);
    }

    public ListView<BookCategory> getCategoryListView() {
        return categoryListView;
    }

    public TextField getCategoryNameField() {
        return categoryNameField;
    }

    public Button getAddCategoryButton() {
        return addCategoryButton;
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