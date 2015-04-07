package com.textbook.sebastiaan.textbooks;

public class Assignment {

    private long id;
    private String bookName, authorName;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return bookName + "-" + authorName;
    }
}
