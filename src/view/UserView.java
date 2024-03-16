package view;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import model.User;
import filehandling.UserDAO;
import model.UserType;

import java.util.Optional;

public class UserView {

    private final UserDAO userDAO;

    public UserView(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public int showMenuOptions() {
        ChoiceDialog<Integer> choiceDialog = new ChoiceDialog<>(1, 2, 3, 4, 5);
        choiceDialog.setTitle("Menu Options");
        choiceDialog.setHeaderText(null);
        choiceDialog.setContentText("Select an option:");

        Optional<Integer> result = choiceDialog.showAndWait();

        return result.orElse(0);
    }

    public Optional<Pair<String, User>> showAddEmployeeDialog() {
        Dialog<Pair<String, User>> dialog = new Dialog<>();
        dialog.setTitle("Adding a New Employee");

        ButtonType addButton = new ButtonType("Add", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");
        PasswordField passwordField = new PasswordField();
        TextField employeeNameField = new TextField();
        employeeNameField.setPromptText("Enter employee name");
        TextField employeeBirthdayField = new TextField();
        employeeBirthdayField.setPromptText("Enter employee birthday");
        TextField employeePhoneField = new TextField();
        employeePhoneField.setPromptText("Enter employee phone");
        TextField employeeEmailField = new TextField();
        employeeEmailField.setPromptText("Enter employee email");
        TextField employeeSalaryField = new TextField();
        employeeSalaryField.setPromptText("Enter employee salary");

        ChoiceBox<UserType> userTypeChoiceBox = new ChoiceBox<>();
        userTypeChoiceBox.getItems().addAll(UserType.LIBRARIAN, UserType.MANAGER, UserType.ADMINISTRATOR);
        userTypeChoiceBox.getItems().add(0, null);
        userTypeChoiceBox.setValue(null);

        grid.add(new Label("Username:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(new Label("Employee Name:"), 0, 2);
        grid.add(employeeNameField, 1, 2);
        grid.add(new Label("Employee Birthday:"), 0, 3);
        grid.add(employeeBirthdayField, 1, 3);
        grid.add(new Label("Employee Phone:"), 0, 4);
        grid.add(employeePhoneField, 1, 4);
        grid.add(new Label("Employee Email:"), 0, 5);
        grid.add(employeeEmailField, 1, 5);
        grid.add(new Label("Employee Salary:"), 0, 6);
        grid.add(employeeSalaryField, 1, 6);
        grid.add(new Label("User Type:"), 0, 7);
        grid.add(userTypeChoiceBox, 1, 7);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                try {
                    String username = usernameField.getText();
                    String password = passwordField.getText();
                    String employeeName = employeeNameField.getText();
                    String employeeBirthday = employeeBirthdayField.getText();
                    String employeePhone = employeePhoneField.getText();
                    String employeeEmail = employeeEmailField.getText();

                    if (username.isEmpty() || password.isEmpty() || employeeName.isEmpty() ||
                            employeeBirthday.isEmpty() || employeePhone.isEmpty() || employeeEmail.isEmpty()) {
                        showAlert("Please fill in all required fields.");
                        return null;
                    }

                    double employeeSalary = Double.parseDouble(employeeSalaryField.getText());

                    UserType selectedUserType = userTypeChoiceBox.getValue();

                    return new Pair<>(username, new User(
                            username,
                            password,
                            selectedUserType,
                            employeeName,
                            employeeBirthday,
                            employeePhone,
                            employeeEmail,
                            employeeSalary
                    ));
                } catch (NumberFormatException e) {
                    showAlert("Invalid employee salary. Please enter a numeric value.");
                }
            }
            return null;
        });

        return dialog.showAndWait();
    }

    public Optional<String> showInputDialog(String headerText, String contentText) {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText(headerText);
        inputDialog.setContentText(contentText);

        return inputDialog.showAndWait();
    }

    public void showAlert(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    public Optional<String> deleteEmployee() {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText("Deleting an Employee");
        inputDialog.setContentText("Enter username of the employee to delete:");

        return inputDialog.showAndWait();
    }

    public Optional<String> revokeAccess() {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText("Revoking Access");
        inputDialog.setContentText("Enter username of the employee to revoke access:");

        Optional<String> result = inputDialog.showAndWait();
        result.ifPresent(username -> {
            User selectedEmployee = userDAO.getByUsername(username);

            if (selectedEmployee != null) {
                System.out.println("Before Update - User Type: " + selectedEmployee.getUserType());

                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                confirmation.setHeaderText("Confirmation");
                confirmation.setContentText("Are you sure you want to revoke access for " + selectedEmployee.getUsername() + "?");

                Optional<ButtonType> resultButton = confirmation.showAndWait();

                if (resultButton.isPresent() && resultButton.get() == ButtonType.YES) {
                    if (selectedEmployee.getUserType() == UserType.MANAGER) {
                        boolean updateResult = userDAO.updateUser(selectedEmployee, UserType.LIBRARIAN);

                        System.out.println("After Update - User Type: " + selectedEmployee.getUserType());

                        if (updateResult) {
                            showAlert("Access revoked successfully.");
                        } else {
                            showAlert("Error revoking access.");
                        }
                    } else {
                        showAlert("Access already revoked or not a Manager.");
                    }
                } else {
                    showAlert("Access revocation canceled.");
                }
            } else {
                showAlert("Employee not found.");
            }
        });

        return result;
    }



    public Optional<String> updateEmployee() {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setHeaderText("Updating an Employee");
        inputDialog.setContentText("Enter username of the employee to update:");

        return inputDialog.showAndWait();
    }

    public Pair<String, String> showUpdateDialog(User selectedEmployee) {
        Dialog<Pair<String, String>> updateDialog = new Dialog<>();
        updateDialog.setTitle("Updating Employee");
        updateDialog.setHeaderText("Choose a data field to edit:");

        ButtonType updateButton = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        updateDialog.getDialogPane().getButtonTypes().addAll(updateButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton nameRadioButton = new RadioButton("Employee Name");
        RadioButton birthdayRadioButton = new RadioButton("Employee Birthday");
        RadioButton phoneRadioButton = new RadioButton("Employee Phone");
        RadioButton emailRadioButton = new RadioButton("Employee Email");
        RadioButton salaryRadioButton = new RadioButton("Employee Salary");

        nameRadioButton.setToggleGroup(toggleGroup);
        birthdayRadioButton.setToggleGroup(toggleGroup);
        phoneRadioButton.setToggleGroup(toggleGroup);
        emailRadioButton.setToggleGroup(toggleGroup);
        salaryRadioButton.setToggleGroup(toggleGroup);

        grid.add(nameRadioButton, 0, 0);
        grid.add(birthdayRadioButton, 0, 1);
        grid.add(phoneRadioButton, 0, 2);
        grid.add(emailRadioButton, 0, 3);
        grid.add(salaryRadioButton, 0, 4);

        updateDialog.getDialogPane().setContent(grid);

        updateDialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButton) {
                RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
                if (selectedRadioButton != null) {
                    String fieldToUpdate = selectedRadioButton.getText();
                    TextInputDialog inputDialog = new TextInputDialog();
                    inputDialog.setHeaderText("Enter new " + fieldToUpdate + ":");
                    Optional<String> result = inputDialog.showAndWait();
                    return result.map(newValue -> new Pair<>(fieldToUpdate, newValue)).orElse(null);
                } else {
                    showAlert("Invalid choice.");
                }
            }
            return null;
        });

        return updateDialog.showAndWait().orElse(null);
    }


    public void handleUpdate(User selectedEmployee, String fieldToUpdate, String updatedValue) {
        switch (fieldToUpdate) {
            case "Employee Name":
            case "Employee Birthday":
            case "Employee Phone":
            case "Employee Email":
            case "Employee Salary":
                updateEmployeeField(selectedEmployee, fieldToUpdate, updatedValue);
                break;
            default:
                showAlert("Invalid choice.");
        }
    }
    public void updateEmployeeField(User selectedEmployee, String field, String updatedValue) {
        switch (field) {
            case "Employee Name":
                selectedEmployee.setEmployeeName(updatedValue);
                break;
            case "Employee Birthday":
                selectedEmployee.setEmployeeBirthday(updatedValue);
                break;
            case "Employee Phone":
                selectedEmployee.setEmployeePhone(updatedValue);
                break;
            case "Employee Email":
                selectedEmployee.setEmployeeEmail(updatedValue);
                break;
            case "Employee Salary":
                try {
                    double salary = Double.parseDouble(updatedValue);
                    selectedEmployee.setEmployeeSalary(salary);
                } catch (NumberFormatException e) {
                    showAlert("Invalid salary format. Please enter a valid number.");
                }
                break;
            default:
                showAlert("Invalid field.");
        }
    }
}
