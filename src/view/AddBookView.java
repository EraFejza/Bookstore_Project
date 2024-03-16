package view;

import controller.BookController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;

public class AddBookView extends Application{
    public void start(Stage stage) throws Exception{
		//the grid 
		GridPane LP = new GridPane();
		LP.setHgap(15);
		LP.setVgap(15);
		LP.setPadding(new Insets(15, 15, 15, 15));
		LP.setAlignment(Pos.CENTER);

		//title 
		Label titleL = new Label("title");
		TextField titleF = new TextField();
		LP.add(titleL, 0, 0);
		LP.add(titleF, 1, 0);
		
		//author
		Label authorL = new Label("author");
		TextField authorF = new TextField();
		LP.add(authorL, 0, 1);
		LP.add(authorF, 1, 1);
		
		//publication 
		Label pubL = new Label("publications");
		TextField pubF = new TextField();
		LP.add(pubL, 0, 2);
		LP.add(pubF, 1, 2);
		
		//category
		Label catL = new Label("category");
		TextField catF = new TextField();
		LP.add(catL, 0, 3);
		LP.add(catF, 1, 3);
		
		//ISBN
		Label ISBNL = new Label("ISBN");
		TextField ISBNF = new TextField();
		LP.add(ISBNL, 0, 4);
		LP.add(ISBNF, 1, 4);
		
		//quality 
		Label quantityL = new Label("quality");
		TextField quantityF = new TextField();
		LP.add(quantityL, 0, 5);
		LP.add(quantityF, 1, 5);
		
		//orgPrice  original 
		Label orgPriceL = new Label("original");
		TextField orgPriceF = new TextField();
		LP.add(orgPriceL, 0, 6);
		LP.add(orgPriceF, 1, 6);
		
		//sellPrice 
		Label sellPriceT = new Label("sell price");
		TextField sellPriceF = new TextField();
		LP.add(sellPriceT, 0, 7);
		LP.add(sellPriceF, 1, 7);
		
		
		Button ADD = new Button("ADD");
		ADD.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String title = titleF.getText(); 
				String author = authorF.getText(); 
				String pub = pubF.getText(); 
				String cat = catF.getText(); 
				String ISBN = ISBNF.getText(); 
				int quantity = Integer.parseInt(quantityF.getText()); 
				double orgPrice = Double.parseDouble(orgPriceF.getText());
				double sellPrice = Double.parseDouble(sellPriceF.getText());
				
				Book book = new Book(title, author, pub, cat, ISBN, quantity, orgPrice, sellPrice); 
				BookController BC = new BookController();
				boolean added = BC.AddBook(book); 
		        if (added) {
	                Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                alert.setTitle("Book added");
	                alert.setContentText("the book" + book.getTitle() + "was added");
	                alert.setHeaderText(null);
	                alert.showAndWait();
	            } else {
	                Alert alert = new Alert(Alert.AlertType.ERROR);
	                alert.setTitle("Book Not added");
	                alert.setHeaderText(null);
	                alert.setContentText("an error occured and the book was not added.");
	                alert.showAndWait();
	            }				
				
			}
		});

        VBox layout = new VBox();
        layout.getChildren().addAll(titleL, titleF, authorL, authorF, pubL, pubF, catL, catF, ISBNL, ISBNF
        							,quantityL, quantityF, orgPriceL, orgPriceF, sellPriceT,sellPriceF, ADD);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 400);

        stage.setScene(scene);
        stage.show();
        
	}
    public static void main(String[] args) {
        launch(args);
    }
}