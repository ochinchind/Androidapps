package com.example.wspnew.utils;

import com.example.wspnew.activities.ManagerActivity;
import com.example.wspnew.classes.Comment;
import com.example.wspnew.classes.News;
import com.example.wspnew.enums.Gender;
import com.example.wspnew.users.Manager;

public class updateNews extends Thread {
    Comment comment;
    News news;
    public updateNews(Comment comment, News news) {
        this.comment = comment;
        this.news = news;
    }

    @Override
    public void run() {
        new Manager("a", "b", "c", "d","e", Gender.MALE, "asd", 123).deleteNews(news.getTitle(), news.getDescription());
        news.getComments().add(comment);
        new Manager("a", "b", "c", "d","e", Gender.MALE, "asd", 123).addNews(news);
    }
}
