package view;



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

public class FindBookView extends Application{
    public void start(Stage stage) throws Exception{
        // title
        Label titleL = new Label("Title:");
        TextField titleF = new TextField();

        // Create a button to search for the book
        Button search = new Button("Search");
        search.setOnAction(event -> {
            String title = titleF.getText();
            BookController bc = new BookController(); 
            Book book = bc.findBookByTitle(title);
            if (book != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Book Found");
                alert.setHeaderText(null);
                alert.setContentText("The book " + book.getTitle() + " was found."
                		+ "\n" + book.toString());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Book Not Found");
                alert.setHeaderText(null);
                alert.setContentText("The book " + title + " was not found.");
                alert.showAndWait();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleL, titleF, search);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 200);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
