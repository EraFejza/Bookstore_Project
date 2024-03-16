package model;


import java.util.ArrayList;
import java.util.List;

import filehandling.AuthorDAO;
import filehandling.BookCategoryDAO;
import filehandling.BookDAO;

public class Manager {
   private List<Book> lowStockBooks;
   private BookDAO bookDAO;
   private BookCategoryDAO bookCategoryDAO;
   private AuthorDAO authorDAO;

   public Manager() {
       this.lowStockBooks = new ArrayList<>();
   }

   public List<Book> getLowStockBooks() {
       return lowStockBooks;
   }

   public void setLowStockBooks(List<Book> lowStockBooks) {
       this.lowStockBooks = lowStockBooks;
   }
   public void setBookDAO(BookDAO bookDAO) {
       this.bookDAO = bookDAO;
   }

   public void setBookCategoryDAO(BookCategoryDAO bookCategoryDAO) {
       this.bookCategoryDAO = bookCategoryDAO;
   }

   public void setAuthorDAO(AuthorDAO authorDAO) {
       this.authorDAO = authorDAO;
   }
   // Add other model-related methods as needed
}
