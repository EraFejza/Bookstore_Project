package model;

import java.io.Serializable;

public class AuthorModel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String authorName;

    public AuthorModel(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    @Override
    public String toString() {
        return authorName;
    }
}
