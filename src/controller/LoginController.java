package controller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import filehandling.UserDAO;
import model.User;
import model.UserType;

public class LoginController {

    public static final String FILE_PATH = "database/users.dat";
    private static final File DATA_FILE = new File(FILE_PATH);
    private final UserDAO userDAO;

    private ArrayList<User> users;

    public LoginController() {
        this.userDAO = new UserDAO();
        users = new ArrayList<>(userDAO.getAll());
    }

    public void addUser(User u) {
        this.users.add(u);
    }

    public boolean signUp(String username, String password, UserType userType, String name, String birthday,
            String PhoneNumber, String email, double salary) {
        boolean found = false;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                found = true;
                break; // user is found
            }
        }
        if (found) {
            return false; // user exists
        } else {
            User u = new User(username, password, userType, name, birthday, PhoneNumber, email, salary);
            addUser(u);
            users.add(u);
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
                outputStream.writeObject(users);
                return true;
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
        }
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
