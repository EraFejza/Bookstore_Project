package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;
import model.User;
import filehandling.BookDAO;
import filehandling.UserDAO;
import controller.CostController;

import java.util.List;

public class CostView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
 
        BookDAO bookDAO = BookDAO.getInstance();  
        UserDAO userDAO = UserDAO.getInstance();  

        List<Book> books = bookDAO.getAll();
        List<User> users = userDAO.getAll();

        // Calculate total cost
        double totalCost = CostController.calculateTotalCost(books, users);

        // Create JavaFX components
        Label totalCostLabel = new Label("Total Cost: $" + totalCost);

        // Create layout
        VBox vbox = new VBox(totalCostLabel);

        // Create scene
        Scene scene = new Scene(vbox, 300, 200);

        // Set stage properties
        primaryStage.setTitle("Bookstore");
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }
}
