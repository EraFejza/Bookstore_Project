package view;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import model.UserType;
import filehandling.UserDAO;

import java.io.IOException;
import java.util.List;
public class ShowUserData extends Application{


    private final UserDAO userDAO = UserDAO.getInstance();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User Data Table");

        TableView<User> tableView = createTableView();

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> refreshTableView(tableView));

        VBox layout = new VBox(10);
        layout.setPadding(new javafx.geometry.Insets(10));
        layout.getChildren().addAll(tableView, refreshButton);

        Scene scene = new Scene(layout, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.show();

        refreshTableView(tableView);
    }

    private TableView<User> createTableView() {
        TableView<User> tableView = new TableView<>();

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, UserType> userTypeColumn = new TableColumn<>("User Type");
        userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));

        TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
       passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<User, String> employeeNameColumn = new TableColumn<>("Employee Name");
        employeeNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));

        TableColumn<User, String> employeeBirthdayColumn = new TableColumn<>("Employee Birthday");
        employeeBirthdayColumn.setCellValueFactory(new PropertyValueFactory<>("employeeBirthday"));

        TableColumn<User, String> employeePhoneColumn = new TableColumn<>("Employee Phone");
        employeePhoneColumn.setCellValueFactory(new PropertyValueFactory<>("employeePhone"));

        TableColumn<User, String> employeeEmailColumn = new TableColumn<>("Employee Email");
        employeeEmailColumn.setCellValueFactory(new PropertyValueFactory<>("employeeEmail"));

        TableColumn<User, Double> employeeSalaryColumn = new TableColumn<>("Employee Salary");
        employeeSalaryColumn.setCellValueFactory(new PropertyValueFactory<>("employeeSalary"));

        // The order of attributes in the User constructor:
        // (username, password, userType, employeeName, employeeBirthday, employeePhone, employeeEmail, employeeSalary)
        tableView.getColumns().addAll(usernameColumn, userTypeColumn,passwordColumn, employeeNameColumn,
                employeeBirthdayColumn, employeePhoneColumn, employeeEmailColumn, employeeSalaryColumn);

        return tableView;
    }


    private void refreshTableView(TableView<User> tableView) {
        try {
            ObservableList<User> users = userDAO.getAll();
            tableView.setItems(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

