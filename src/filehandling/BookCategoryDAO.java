package filehandling;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BookCategory;

import java.io.*;

public class BookCategoryDAO {
    public static final String FILE_PATH = "database/categories.dat";
    private static final File DATA_FILE = new File(FILE_PATH);
    private final ObservableList<BookCategory> categories = FXCollections.observableArrayList();
    private static BookCategoryDAO instance;
    public BookCategoryDAO() {
        if (!DATA_FILE.exists()) {
            try {
                DATA_FILE.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            loadCategoriesFromFile();
        }
    }

    public ObservableList<BookCategory> getAll() {
        return categories;
    }
    public static BookCategoryDAO getInstance() {
        if (instance == null) {
            instance = new BookCategoryDAO();
        }
        return instance;
    }

    private void loadCategoriesFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            Object obj;
            while ((obj = inputStream.readObject()) != null) {
                if (obj instanceof BookCategory) {
                    categories.add((BookCategory) obj);
                }
            }
        } catch (EOFException ignored) {
            // End of file reached, do nothing
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public boolean create(BookCategory category) {
        categories.add(category);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            for (BookCategory existingCategory : categories) {
                outputStream.writeObject(existingCategory);
            }
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public boolean categoryExists(String categoryName) {
        for (BookCategory category : categories) {
            if (category.toString().equalsIgnoreCase(categoryName)) {
                return true;
            }
        }
        return false;
    }



  }