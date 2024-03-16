package view;


import java.io.IOException;

import controller.BookController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;

public class CreateBillView extends Application {
    public void start(Stage stage) throws IOException{
    	
    	Label ISBNL = new Label("ISBN");
        TextField ISBNF = new TextField();

        // Create a button to search for the book
        Button buy = new Button("buy");
        buy.setOnAction(event -> {
            String ISBN = ISBNF.getText();
            BookController bc = new BookController(); 
            Book book = bc.findBookByISBN(ISBN);
            if (book == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Book Not Found");
                alert.setHeaderText(null);
                alert.setContentText("The book with ISBN " + ISBN + " was not found.");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("The book with ISBN " + ISBN + " was bought.");
					bc.createBill(ISBN, 1);
				
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(ISBNL, ISBNF, buy);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 200);

        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}