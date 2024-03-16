package controller;
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
import view.UserView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserController {
	 private final UserDAO userDAO;
	    private final UserView userView;
	    private static ArrayList<User> users;


	    public List<User> getUsers() {
	        if (this.users == null) {
	            return Collections.emptyList(); 
	        }

	        return new ArrayList<>(this.users);
	    }


	    public UserController(UserDAO userDAO, UserView userView) {
	        this.userDAO = userDAO;
	        this.userView = userView;
	    }

                 public void addEmployee() {
	            Optional<Pair<String, User>> result = userView.showAddEmployeeDialog();

	            result.ifPresent(newEmployee -> {
	                if (userDAO.create(newEmployee.getValue())) {
	                    userView.showAlert("Employee added successfully.");
	                } else {
	                    userView.showAlert("Failed to add employee. Please try again.");
	                }
	            });
	        }

	        public void deleteEmployee() {
	            Optional<String> result = userView.deleteEmployee();

	            result.ifPresent(username -> {
	                User selectedEmployee = userDAO.getByUsername(username);

	                if (selectedEmployee != null) {
	                    boolean deleteResult = userDAO.delete(selectedEmployee);

	                    if (deleteResult) {
	                        userView.showAlert("Employee deleted successfully.");
	                    } else {
	                        userView.showAlert("Error deleting employee.");
	                    }
	                } else {
	                    userView.showAlert("Employee not found.");
	                }
	            });
	        }
	        public void updateEmployee() {
	            Optional<String> result = userView.updateEmployee();

	            result.ifPresent(username -> {
	                User selectedEmployee = userDAO.getByUsername(username);

	                if (selectedEmployee != null) {
	                    Pair<String, String> updateInfo = userView.showUpdateDialog(selectedEmployee);
	                    userView.handleUpdate(selectedEmployee, updateInfo.getKey(), updateInfo.getValue());
	                } else {
	                    userView.showAlert("Employee not found.");
	                }
	            });
	        }
	        public void revokeAccess() {
	           userView.revokeAccess()  ;
	      }

	        public void showAlert(String message) {
	            userView.showAlert(message);
	        }

	        public Parent getUserView() {
	            return new VBox();
	        }
	    }
