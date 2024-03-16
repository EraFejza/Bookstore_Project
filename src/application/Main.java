package application;

import controller.BookController;
import controller.CategoryController; 
import controller.ManagerController;
import controller.UserController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import filehandling.AuthorDAO;
import filehandling.BookCategoryDAO;
import filehandling.BookDAO;
import filehandling.UserDAO;
import model.AuthorModel;
import model.Book;
import model.BookCategory;
import model.User;
import view.AdministratorMenu;
import view.LoginView;
import view.ManagerView;
import view.UserView;
import controller.AuthorController;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;



public class Main extends Application {
    private static final String BOOKFILE_PATH = "database/books.dat";
    private static final String USERFILE_PATH = "database/users.dat";
    private static final String CATEGORY_FILE_PATH = "database/categories.dat";
    private static final String AUTHOR_FILE_PATH = "database/authors.dat";

    public static void main(String[] args) {
        seedData();
        seedUserData();
        seedCategoryData();
        seedAuthorData();
        launch(args);
    }

    private static void seedUserData() {
        File file = new File(USERFILE_PATH);
        if (file.length() == 0) {
            User.Administrator admin1 = new User.Administrator("admin1", "adminPass1");
            admin1.setEmployeeName("John Admin");
            admin1.setEmployeeBirthday("01-01-1980");
            admin1.setEmployeePhone("123-456-7890");
            admin1.setEmployeeEmail("admin1@example.com");
            admin1.setEmployeeSalary(50000.0);

            User.Manager manager1 = new User.Manager("manager1", "managerPass1");
            manager1.setEmployeeName("Mike Manager");
            manager1.setEmployeeBirthday("03-03-1990");
            manager1.setEmployeePhone("111-222-3333");
            manager1.setEmployeeEmail("manager1@example.com");
            manager1.setEmployeeSalary(60000.0);

            User.Librarian employee1 = new User.Librarian("employee1", "employeePass1");
            employee1.setEmployeeName("Alice Employee");
            employee1.setEmployeeBirthday("04-04-1995");
            employee1.setEmployeePhone("444-555-6666");
            employee1.setEmployeeEmail("employee1@example.com");
            employee1.setEmployeeSalary(40000.0);

            User.Librarian employee2 = new User.Librarian("employee2", "employeePass2");
            employee2.setEmployeeName("Bob Employee");
            employee2.setEmployeeBirthday("05-05-2000");
            employee2.setEmployeePhone("777-888-9999");
            employee2.setEmployeeEmail("employee2@example.com");
            employee2.setEmployeeSalary(45000.0);

            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
                User[] users = {admin1, manager1, employee1, employee2};
                for (User user : users) {
                    outputStream.writeObject(user);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void seedData() {
        File file = new File(BOOKFILE_PATH);
        if (file.length() == 0) {
            Book[] books = {
                    new Book("Book One", "AuthorX", "SupplierA", "Fiction", "ISBN001", 50, 25.0, 30.0),
                    new Book("Book Two", "AuthorY", "SupplierB", "Non-Fiction", "ISBN002", 60, 30.0, 35.0),
                    new Book("Book Three", "AuthorZ", "SupplierC", "Science", "ISBN003", 40, 20.0, 25.0),
                    new Book("Book Four", "AuthorW", "SupplierD", "History", "ISBN004", 55, 28.0, 32.0),
                    new Book("Book Five", "AuthorV", "SupplierE", "Mystery", "ISBN005", 48, 26.0, 31.0),
                    new Book("Book Six", "AuthorU", "SupplierF", "Fantasy", "ISBN006", 42, 23.0, 28.0),
                    new Book("Book Seven", "AuthorT", "SupplierG", "Adventure", "ISBN007", 37, 18.0, 22.0),
                    new Book("Book Eight", "AuthorS", "SupplierH", "Romance", "ISBN008", 63, 32.0, 37.0),
                   new Book("Book Nine", "AuthorR", "SupplierI", "Biography", "ISBN009", 46, 24.0, 29.0),
                    new Book("Book Ten", "AuthorQ", "SupplierJ", "Thriller", "ISBN010", 52, 27.0, 33.0),
                    new Book("Book Eleven", "AuthorP", "SupplierK", "Comedy", "ISBN011", 38, 19.0, 23.0),
                    new Book("Book Twelve", "AuthorO", "SupplierL", "Drama", "ISBN012", 44, 22.0, 27.0),
                    new Book("Book Thirteen", "AuthorN", "SupplierM", "Horror", "ISBN013", 41, 21.0, 26.0),
                    new Book("Book Fourteen", "AuthorM", "SupplierN", "Self-Help", "ISBN014", 49, 25.0, 30.0),
                    new Book("Book Fifteen", "AuthorL", "SupplierO", "Poetry", "ISBN015", 55, 28.0, 33.0)
            };
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
                for (Book book : books) {
                    outputStream.writeObject(book);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    private static void seedCategoryData() {
        File categoryFile = new File(CATEGORY_FILE_PATH);

        if (categoryFile.length() == 0) {
            String[] categories = {
                    "Fiction",
                    "Non-Fiction",
                    "Science",
                    "History",
                    "Mystery",
                    "Fantasy",
                    "Adventure",
                    "Romance",
                    "Biography",
                    "Thriller",
                    "Comedy",
                    "Drama",
                    "Horror",
                    "Self-Help",
                    "Poetry"
            };

            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(categoryFile))) {
                for (String category : categories) {
                    outputStream.writeObject(new BookCategory(category));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void seedAuthorData() {
        File authorFile = new File(AUTHOR_FILE_PATH);

        if (authorFile.length() == 0) {
            String[] authors = {
                    "AuthorX",
                    "AuthorY",
                    "AuthorZ",
                    "AuthorW",
                    "AuthorV",
                    "AuthorU",
                    "AuthorT",
                    "AuthorS",
                    "AuthorR",
                    "AuthorQ",
                    "AuthorP",
                    "AuthorO",
                    "AuthorN",
                    "AuthorM"
            };

            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(authorFile))) {
                for (String author : authors) {
                    outputStream.writeObject(new AuthorModel(author));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    @Override
    public void start(Stage primaryStage) {
        BookDAO bookDAO = BookDAO.getInstance();
        BookController bookController = new BookController(bookDAO);
        UserDAO userDAO = UserDAO.getInstance();
        
        BookCategoryDAO categoryDAO = new BookCategoryDAO();
        CategoryController categoryController = new CategoryController();
        
        AuthorDAO authorDAO = new AuthorDAO();
        AuthorController authorController = new AuthorController();
        
        ManagerController managerController = new ManagerController(bookDAO, categoryDAO, authorDAO);
        //managerController.run();

        Scene bookScene = new Scene(bookController.getBookView(), 800, 600);
        Scene categoryScene = new Scene(categoryController.getView(), 800, 600);
        Scene authorScene = new Scene(authorController.getView(), 800, 600);
        
        Stage bookStage = new Stage();
        bookStage.setTitle("Book Management System");
        bookStage.setScene(bookScene);

        Stage userStage = new Stage();
        userStage.setTitle("User Management System");
 
        Stage categoryStage = new Stage();
        categoryStage.setTitle("List of available categories");
        categoryStage.setScene(categoryScene);
        
        Stage authorStage = new Stage();
        authorStage.setTitle("List of available authors");
        authorStage.setScene(authorScene);
        
     
     // bookStage.show();
        // userStage.show();
        
  
/*
         Scene bookScene = new Scene(bookController.getBookView(), 800, 600);
        Stage bookStage = new Stage();
        bookStage.setTitle("Book Management System");
        bookStage.setScene(bookScene);
         bookStage.show();*/
        UserView userView = new UserView(userDAO); 
        UserController userController = new UserController(userDAO, userView);
     		LoginView lg = new LoginView();
     		primaryStage.setTitle("Log In");
     		primaryStage.setScene(lg.showView(primaryStage));
     		primaryStage.show();
 
  
 
    	

    	}
     
    }
   
