package controller;

import filehandling.BookCategoryDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.BookCategory;

public class CategoryController {
    private final BookCategoryDAO categoryDAO;
    private final VBox view;

    public CategoryController() {
        this.categoryDAO = new BookCategoryDAO();
        this.view = initialize();
    }

    private VBox initialize() {
        ListView<BookCategory> categoryListView = new ListView<>(categoryDAO.getAll());

        TextField categoryNameField = new TextField();
        categoryNameField.setPromptText("Enter category name");

        Button addCategoryButton = new Button("Add Category");
        addCategoryButton.setOnAction(event -> {
            String categoryName = categoryNameField.getText();
            if (addCategory(categoryName)) {
                categoryNameField.clear();
                categoryListView.setItems(categoryDAO.getAll());
            }
        });

        HBox addCategoryBox = new HBox(categoryNameField, addCategoryButton);

        VBox root = new VBox(categoryListView, addCategoryBox);
        return root;
    }

    public VBox getView() {
        return view;
    }

    public boolean addCategory(String categoryName) {
        if (!categoryDAO.categoryExists(categoryName) && !categoryName.isEmpty()) {
            BookCategory categoryModel = new BookCategory(categoryName);
            if (categoryDAO.create(categoryModel)) {
                showAlert("Success", "Category added successfully.");
                return true;
            } else {
                showAlert("Error", "Failed to add category.");
            }
        } else {
            showAlert("Error", "Category already exists or is empty.");
        }
        return false;
    }

    // Add other methods as needed for category management

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}