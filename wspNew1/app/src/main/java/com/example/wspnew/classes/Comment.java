package com.example.wspnew.classes;

import com.example.wspnew.users.User;

public class Comment {
    private User author;
    private String date;
    private String text;

    public Comment(User author, String date, String text) {
        this.author = author;
        this.date = date;
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
