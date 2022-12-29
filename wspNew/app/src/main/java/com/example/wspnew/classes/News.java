package com.example.wspnew.classes;

import com.example.wspnew.enums.NewsType;
import com.example.wspnew.users.Manager;

import java.util.Vector;

public class News {
    private String title;
    private String description;
    private String date;
    private Manager author;
    private NewsType newsType;
    private Vector<Comment> comments = new Vector<>();

    public News(String title, String description, String date, Manager author, NewsType newsType) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.author = author;
        this.newsType = newsType;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Manager getAuthor() {
        return author;
    }

    public void setAuthor(Manager author) {
        this.author = author;
    }

    public NewsType getNewsType() {
        return newsType;
    }

    public void setNewsType(NewsType newsType) {
        this.newsType = newsType;
    }

    public Vector<Comment> getComments() {
        return comments;
    }

    public void setComments(Vector<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        String comment = "";
        for(int i = 0; i < comments.size(); i++) {
            comment += comments.get(i).toString() + "\n";
        }
        return "News{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", author=" + author.toString() +
                ", newsType=" + newsType +
                ", comments=" + comment +
                '}';
    }
}
