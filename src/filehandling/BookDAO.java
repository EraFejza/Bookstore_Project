package filehandling;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Book;

import java.io.*;
import java.util.List;

public class BookDAO {
    public static final String FILE_PATH = "database/books.dat";
    private static final File DATA_FILE = new File(FILE_PATH);
    private final ObservableList<Book> books = FXCollections.observableArrayList();
    private static BookDAO instance;

    public BookDAO() {
        if (!DATA_FILE.exists()) {
            try {
                DATA_FILE.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static BookDAO getInstance() {
        if (instance == null) {
            instance = new BookDAO();
        }
        return instance;
    }

    public ObservableList<Book> getAll() {
        if (books.isEmpty()) {
            loadBooksFromFile();
        }
        return books;
    }

    private void loadBooksFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            Object obj;
            while ((obj = inputStream.readObject()) != null) {
                if (obj instanceof Book) {
                    books.add((Book) obj);
                }
            }
        } catch (EOFException ignored) {

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public boolean create(Book book) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE, true))) {
            books.add(book);
            outputStream.writeObject(book);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean delete(Book book) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            for (Book b : books) {
                if (!b.equals(book))
                    outputStream.writeObject(b);
            }
            books.remove(book);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteAll(List<Book> booksToRemove) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            for (Book b : books) {
                if (!booksToRemove.contains(b))
                    outputStream.writeObject(b);
            }
            books.removeAll(booksToRemove);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean updateAll() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            for (Book b : books) {
                outputStream.writeObject(b);
            }
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
