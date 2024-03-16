package filehandling;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import model.User;
import model.UserType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {
    public static final String FILE_PATH = "database/users.dat";
    private static final File DATA_FILE = new File(FILE_PATH);
    private final ObservableList<User> users = FXCollections.observableArrayList();
    private static UserDAO instance;

    public UserDAO() {
        if (!DATA_FILE.exists()) {
            try {
                DATA_FILE.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    public ObservableList<User> getAll() {
        if (users.isEmpty()) {
            loadUsersFromFile(FILE_PATH); 
        }
        return users;
    }

    public List<User> loadUsersFromFile(String filePath) {
        if (!DATA_FILE.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            List<User> loadedUsers = new ArrayList<>();
            while (true) {
                try {
                    User user = (User) ois.readObject();
                    loadedUsers.add(user);
                } catch (EOFException e) {
                    break;
                }
            }
            users.setAll(loadedUsers);
            return loadedUsers;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    public boolean create(User user) {
        users.add(user);
        return updateAll(users);
    }


    public boolean delete(User user) {
        users.remove(user);
        return updateAll(users);
    }

    public boolean updateAll(List<User> updatedUsers) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            for (User user : updatedUsers) {
                outputStream.writeObject(user);
            }
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }



    public boolean deleteAll(List<User> usersToRemove) {
        users.removeAll(usersToRemove);

        return updateAll(users);
    }


    public boolean updateUser(User user, Object updatedValue) {
        List<User> updatedUsers = new ArrayList<>();

        for (User u : users) {
            if (u.equals(user)) {
                if (updatedValue instanceof UserType) {
                    u.setUserType((UserType) updatedValue);
                } else if (updatedValue instanceof Double) {
                    u.setEmployeeSalary((Double) updatedValue);
                }
            }
            updatedUsers.add(u);
        }

        return updateAll(updatedUsers);
    }



    public User getByUsername(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    public List<User> getAllUsers() throws IOException, ClassNotFoundException {
        List<User> users = new ArrayList<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            while (true) {
                try {
                    User user = (User) inputStream.readObject();
                    users.add(user);
                } catch (EOFException e) {
                    break;
                }
            }
        }

        return users;
    }
}