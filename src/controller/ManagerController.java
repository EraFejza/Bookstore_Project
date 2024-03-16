package controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import filehandling.AuthorDAO;
import filehandling.BookCategoryDAO;
import filehandling.BookDAO;
import javafx.scene.control.Alert.AlertType;
import model.AuthorModel;
import model.Book;
import model.BookCategory;
import view.AddBookView;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class ManagerController {
    private final BookDAO bookDAO;
    private final BookCategoryDAO categoryDAO;
    private final AuthorDAO authorDAO;

    public ManagerController(BookDAO bookDAO, BookCategoryDAO categoryDAO, AuthorDAO authorDAO) {
        this.bookDAO = bookDAO;
        this.categoryDAO = categoryDAO;
        this.authorDAO = authorDAO;
    }

    public void run() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Manager Console");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);  //
        vbox.setAlignment(Pos.CENTER);  

        Label label = new Label("Select an option:");
 
        Button categoryListButton = createStyledButton("Category List");
        Button authorListButton = createStyledButton("Authors List");// New button for displaying authors list
        Button exitButton = createStyledExitButton("Exit");
        Button checkLowStockButton = createStyledButton("Check Low Stock");
        Button statisticsButton = createStyledButton("Librarian Performance");
        Button librarianPerformanceButton = createStyledButton("Bookstore Statistics");
        Button addBookButton = new Button("Add Book");
        addBookButton.setOnAction(e -> {
            AddBookView addBookView = new AddBookView();
            try {
                addBookView.start(new Stage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        // this is event handler
        checkLowStockButton.setOnAction(event -> checkLowStock());

        vbox.getChildren().addAll(label, categoryListButton,authorListButton,checkLowStockButton,statisticsButton, librarianPerformanceButton, exitButton);
       
        // Add a ListView to display the category list
        ListView<BookCategory> categoryListView = new ListView<>(categoryDAO.getAll());
        categoryListView.setVisible(false);  // Initially, set it invisible

        // Event handler for the category list button
        categoryListButton.setOnAction(event -> {
            categoryListView.setItems(categoryDAO.getAll());
            categoryListView.setVisible(true);
        });

        vbox.getChildren().add(categoryListView);
        
        
     // Add a ListView to display the category list
        ListView<AuthorModel> authorListView = new ListView<>(authorDAO.getAll());
        authorListView.setVisible(false);  // Initially, set it invisible

        // Event handler for the category list button
      authorListButton.setOnAction(event -> {
          authorListView.setItems(authorDAO.getAll());
           authorListView.setVisible(true);
        });
      
      vbox.getChildren().add(authorListView);
        Scene scene = new Scene(vbox, 800, 600);
        // Set the background color of the scene to light blue
        Paint lightBlueColor = Color.LIGHTBLUE;
        BackgroundFill backgroundFill = new BackgroundFill(lightBlueColor, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        vbox.setBackground(background);

        primaryStage.setScene(scene);

        primaryStage.show();
        
        
    }

    private Button createStyledButton(String buttonText) {
        Button button = new Button(buttonText);
        button.setMinWidth(200);  // Set the minimum width for consistency
        button.setStyle("-fx-border-color: blue;");  // Set blue outline
        return button;
    }

    private Button createStyledExitButton(String buttonText) {
        Button exitButton = createStyledButton(buttonText);
        exitButton.setTextFill(Color.RED);
        exitButton.setFont(Font.font("Arial", 14));  // Set the font size

        // Add an event handler to close the application when the exit button is clicked
        exitButton.setOnAction(event -> Platform.exit());

        return exitButton;
    }

	public BookDAO getBookDAO() {
		return bookDAO;
	}
	 public void checkLowStock() {
         List<Book> lowStockBooks = new ArrayList<>();

         for (Book book : bookDAO.getAll()) {
             if (book.getQuantity() < 5) {
                 lowStockBooks.add(book);
             }
         }

         if (!lowStockBooks.isEmpty()) {
             StringBuilder message = new StringBuilder("Low stock alert!\n\n");
             for (Book lowStockBook : lowStockBooks) {
                 message.append("Title: ").append(lowStockBook.getTitle())
                        .append(", Quantity: ").append(lowStockBook.getQuantity()).append("\n");
             }

             showAlert("Low Stock Alert", message.toString());
         } else {
             showAlert("No Low Stock", "All books are currently in stock.");
         }
     }
	 private void showAlert(String title, String content) {
         Alert alert = new Alert(AlertType.INFORMATION);
         alert.setTitle(title);
         alert.setHeaderText(null);
         alert.setContentText(content);
         alert.showAndWait();
     }
}