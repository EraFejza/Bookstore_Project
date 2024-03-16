package model;

import java.io.Serializable;

public class BookCategory implements Serializable {
    
	private static final long serialVersionUID = 1L;
	public String categoryName;

    public BookCategory(String categoryName) {
        this.categoryName = categoryName;
    }

  

    @Override
    public String toString() {
        return categoryName;
    }
}