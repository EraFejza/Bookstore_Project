package view;

import controller.UserController;
import controller.BookController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import filehandling.UserDAO;
import filehandling.BookDAO;
import model.Book;
import view.UserView;
import controller.CostController;
import controller.UserController;
import model.User;
import java.util.List;

public class AdministratorMenu extends Application {
    private final UserController userController;
    private final BookController bookController;
    private final Label totalCostLabel;

    public AdministratorMenu(UserController userController, BookController bookController) {
        this.userController = userController;
        this.bookController = bookController;
        this.totalCostLabel = new Label("Total Cost is: $0.0");  
        this.totalCostLabel.setVisible(false); 
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Administrator Panel");

        VBox layout = new VBox(10);
        layout.setPadding(new javafx.geometry.Insets(10));

        Button addButton = new Button("Add Employee");
        addButton.setOnAction(e -> userController.addEmployee());

        Button deleteButton = new Button("Delete Employee");
        deleteButton.setOnAction(e -> userController.deleteEmployee());

        Button editButton = new Button("Edit Employee");
        editButton.setOnAction(e -> userController.updateEmployee());

        Button revokeButton = new Button("Revoke Access");
        revokeButton.setOnAction(e -> userController.revokeAccess());

        Button viewButton = new Button("View User Data");
        viewButton.setOnAction(e -> {
            ShowUserData showUserData = new ShowUserData();
            showUserData.start(new Stage());
        });

        Button totalCostButton = new Button("Total Cost");
        totalCostButton.setOnAction(e -> {
            List<Book> books = bookController.getBooks();

            List<User> allUsers = userController.getUsers();
            double totalCost = CostController.calculateTotalCost(books, allUsers);
            totalCostLabel.setText("Total Cost: $" + totalCost);
            totalCostLabel.setVisible(true);
        });



        layout.getChildren().addAll(addButton, deleteButton, editButton, revokeButton, viewButton, totalCostButton, totalCostLabel);

        Scene scene = new Scene(layout, 300, 250);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
