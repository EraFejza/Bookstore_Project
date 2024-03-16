package view;

import controller.BookController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibrarianMenu {
    private final BookController bookController;

    public LibrarianMenu(BookController bookController) {
        this.bookController = bookController;
    }

    public Scene createScene() {
        VBox layout = new VBox(10);
        layout.setPadding(new javafx.geometry.Insets(10));

        Button addBookButton = new Button("Add Book");
        addBookButton.setOnAction(e -> {
            AddBookView addBookView = new AddBookView();
            try {
                addBookView.start(new Stage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button createBillButton = new Button("Create Bill");
        createBillButton.setOnAction(e -> {
            CreateBillView createBillView = new CreateBillView();
            try {
                createBillView.start(new Stage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Button findBookButton = new Button("Find Book");
        findBookButton.setOnAction(e -> {
            FindBookView findBookView = new FindBookView();
            try {
                findBookView.start(new Stage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        layout.getChildren().addAll(addBookButton, createBillButton, findBookButton);

        return new Scene(layout, 300, 200);
    }
}
