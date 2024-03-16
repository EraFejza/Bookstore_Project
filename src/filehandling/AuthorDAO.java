package filehandling;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AuthorModel;
import model.BookCategory;

import java.io.*;

public class AuthorDAO {
    public static final String FILE_PATH = "database/authors.dat";
    private static final File DATA_FILE = new File(FILE_PATH);
    private final ObservableList<AuthorModel> authors = FXCollections.observableArrayList();
    private static AuthorDAO instance;
    public AuthorDAO() {
        if (!DATA_FILE.exists()) {
            try {
                DATA_FILE.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            loadAuthorsFromFile();
        }
    }

    public static AuthorDAO getInstance() {
        if (instance == null) {
            instance = new AuthorDAO();
        }
        return instance;
    }
    public ObservableList<AuthorModel> getAll() {
        return authors;
    }

    private void loadAuthorsFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            Object obj;
            while ((obj = inputStream.readObject()) != null) {
                if (obj instanceof AuthorModel) {
                    authors.add((AuthorModel) obj);
                }
            }
        } catch (EOFException ignored) {
            // End of file reached, do nothing
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public boolean create(AuthorModel authorModel) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            authors.add(authorModel);
            for (AuthorModel existingAuthor : authors) {
                outputStream.writeObject(existingAuthor);
            }
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean authorExists(String authorName) {
        for (AuthorModel author : authors) {
            if (author.getAuthorName().equalsIgnoreCase(authorName)) {
                return true;
            }
        }
        return false;
    }
}
