package controller;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import model.Book;
import view.AddBookView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import filehandling.BookDAO;
import view.AddBookView;
import view.CreateBillView;
import view.FindBookView;
public class BookController {
    private final BookDAO bookDAO;
    private VBox view;
    private static ArrayList<Book> books; 
    public ArrayList<Book> getBooks() {
        return new ArrayList<>(books);
    }
    public static final String FILE_PATH = "database/books.dat";
    private static final File DATA_FILE = new File(FILE_PATH);
 
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
        initialize();
    	books = new ArrayList<Book>(bookDAO.getAll()); 
    }
    public BookController(){
		this.bookDAO = new BookDAO();
		initialize();
		
		books = new ArrayList<Book>(bookDAO.getAll()); 
    }

    private void initialize() {
        ListView<Book> bookListView = new ListView<>(bookDAO.getAll());
        VBox vbox = new VBox(bookListView);
        view = vbox;
    }
    public Book findBook(int x, String b) {
		switch(x) {
		case 1://by title:
		{
			boolean found = false; 
			for(int i=0; i<books.size();i++) {
				 found =b.equals( books.get(i).getTitle());
				if(found) {
					System.out.println("book was found"); 
					return books.get(i); 
				}
			}
			if(!found) {
				System.out.println("book was not found"); 
				return null;

			}
			System.out.println("idk what happend"); 

			break;

		}
		case 2://by ISBN 
		{
			boolean found = false; 
			for(int i=0; i<books.size();i++) {
				 found =b.equals( books.get(i).getISBN());
				if(found) {
					return books.get(i); 
				}
			}
			if(!found) {
				return null;
			}			
			break;
		}

	}
		return null;
	}
	//find book index
	
	public int findBookIndex(int x,String b) {
		switch(x) {
		case 1://by title:
		{
			boolean found = false; 
			for(int i=0; i<books.size();i++) {
				 found =b.equals( books.get(i).getTitle());
				if(found) {
					return i; 
				}
			}
			if(!found) {
				return -1;
			}			
			break;
		}
		case 2://by ISBN 
		{
			boolean found = false; 
			for(int i=0; i<books.size();i++) {
				found =b.equals( books.get(i).getISBN());
				if(found) {
					return i; 
				}
			}
			if(!found) {
				return -1;
			}			
			break;
		}

	}
		return 0;
	}

	
	//Multiple books 
	public ArrayList<Book> findAllBooks(int x, String b){
		ArrayList<Book> RelatedBooks = new ArrayList<Book>(); 
			switch(x) {
			//by author 
			case 3:
			{
				boolean found = false; 
				for(int i=0; i<books.size();i++) {
					 found =b.equals( books.get(i).getAuther());
					if(found) {
						RelatedBooks.add(books.get(i)); 
						found = false; 
					}
				}
				if(RelatedBooks.size() == 0) {
					return null;
				}else {
					return RelatedBooks;
				} 
			}
			//by publication
			case 4:
			{
				boolean found = false; 
				for(int i=0; i<books.size();i++) {
					 found = b.equals( books.get(i).getPublications());
					if(found) {
						RelatedBooks.add(books.get(i)); 
						found = false; 
					}
				}
				if(RelatedBooks.size() == 0) {
					return null;
				}else {
					return RelatedBooks;
				} 
			}
			//by category
			case 5: 
				{
				boolean found = false; 
				for(int i=0; i<books.size();i++) {
					 found =b.equals( books.get(i).getCategory());
					if(found) {
						RelatedBooks.add(books.get(i));
						found = false;
					}
				}
				if(RelatedBooks.size() == 0) {
					return null;
				}else {
					return RelatedBooks;
				}
			}
		}
			return null;	
	}

		
	public void createBill(String b, int q) {
		CreateBillView cbv = new CreateBillView(); 
		
	    System.out.println("I found the book");
	    int i = findBookIndex(2, b);

	    File newFile = new File("Bill.txt");

	    try (PrintWriter nfw = new PrintWriter(new FileWriter(newFile, true))) {
	        System.out.println("File is " + newFile.getAbsolutePath());
	        
	        if (i >= 0 ) {
	            LocalDateTime currentTime = LocalDateTime.now(); 
	            nfw.println(currentTime); 
	            nfw.println(books.get(i).toString());
	            books.get(i).setQuantity(books.get(i).getQuantity()-1); 
	        } else {
	            System.out.println("Invalid book index");
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("Error writing to file: " + e.getMessage());
	    }
	}

	
	public boolean AddBook(Book book) {
		int i = findBookIndex(1, book.getTitle()); 
		if(i<0) {
			books.add(book); 
		}
		else {
			books.get(i).setQuantity(books.get(i).getQuantity() + 1);
		}
		
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
	
	
	public Book findBookByISBN(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                return book;   
            }
        }
        return null;	}
	
    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;   
            }
        }
        return null;
    }

    public VBox getBookView() {
        return view;
    }
  
}