package view;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import controller.LoginController;
import controller.ManagerController;
import model.User;
import controller.UserController;
import filehandling.UserDAO;
import view.AdministratorMenu;
import controller.AuthorController;
import controller.BookController;
import controller.CategoryController;
import filehandling.BookDAO;
import filehandling.AuthorDAO;
import filehandling.BookCategoryDAO;
import view.ManagerView;

public class LoginView {
	UserDAO userDAO = UserDAO.getInstance();
	BookDAO bookDAO= BookDAO.getInstance();
	AuthorDAO authorDAO=AuthorDAO.getInstance();
	BookCategoryDAO bookcategoryDAO= BookCategoryDAO.getInstance();
	
    UserView userView = new UserView(userDAO); 
    UserController userController = new UserController(userDAO, userView);
    BookController bookController = new BookController();
   CategoryController bookcategoryController = new CategoryController();
   AuthorController authorController = new AuthorController();

    private final LoginController loginController;

    public LoginView() {
        this.loginController = new LoginController();
    }
    
    public LoginView(LoginController loginController) {
        this.loginController = loginController;
    }

    public Scene showView(Stage stage) {
        // the grid
        GridPane LP = new GridPane();
        LP.setHgap(15);
        LP.setVgap(15);
        LP.setPadding(new Insets(15, 15, 15, 15));
        LP.setAlignment(Pos.CENTER);

        // the email
        Label email = new Label("Username");
        TextField emailF = new TextField();
        LP.add(email, 0, 0);
        LP.add(emailF, 1, 0);

        // the password
        Label password = new Label("Password");
        PasswordField passwordF = new PasswordField();
        LP.add(password, 0, 1);
        LP.add(passwordF, 1, 1);

        // the login
        Button login = new Button("Log in");
        HBox h = new HBox();
        h.getChildren().addAll(login);
        h.setSpacing(10);
        LP.add(h, 1, 3);

        login.setOnAction(e -> {
            User user = loginController.login(emailF.getText(), passwordF.getText());
            if (user != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Ok");
                alert.showAndWait();

                switch (user.getUserType()) {
                    case LIBRARIAN:
                    	LibrarianMenu librarianMenu = new LibrarianMenu(bookController);
                        Scene librarianScene = librarianMenu.createScene();
                        Stage librarianStage = new Stage();
                        librarianStage.setTitle("Librarian Panel");
                        librarianStage.setScene(librarianScene);
                        librarianStage.show();
                        break;
                    case MANAGER:
                        ManagerController managerController = new ManagerController(bookDAO, bookcategoryDAO, authorDAO);
                        managerController.run();

                        Scene bookScene = new Scene(bookController.getBookView(), 800, 600);
                        Scene categoryScene = new Scene(bookcategoryController.getView(), 800, 600);
                        Scene authorScene = new Scene(authorController.getView(), 800, 600);
                        Scene bookaddScene = new Scene(bookController.getBookView(), 800, 600);

                        Stage bookStage = new Stage();
                        bookStage.setTitle("Book Management System");
                        bookStage.setScene(bookScene);

                        Stage categoryStage = new Stage();
                        categoryStage.setTitle("List of available categories");
                        categoryStage.setScene(categoryScene);

                        Stage authorStage = new Stage();
                        authorStage.setTitle("List of available authors");
                        authorStage.setScene(authorScene);

                       
                        Stage bookaddStage=new Stage();
                        bookaddStage.setTitle("Add bools");
                        bookaddStage.setScene(bookaddScene);
                        managerController.run();

                       
                        categoryStage.show();
                        authorStage.show();

                        break;


                    case ADMINISTRATOR:
                    	  Scene userScene = new Scene(userController.getUserView(), 800, 600);
                          Stage userStage = new Stage();
                          userStage.setTitle("User Management System");
                          userStage.setScene(userScene);
                          userStage.show();
                          AdministratorMenu adminMenu = new AdministratorMenu(userController,bookController);
                          adminMenu.start(userStage);
                        break;
                   
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Enter the correct email or Password");
                alert.showAndWait();
            }
        });

        Scene SC = new Scene(LP, 400, 300);

        return SC;
    }
}
