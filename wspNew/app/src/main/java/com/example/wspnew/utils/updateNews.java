package com.example.wspnew.utils;

import com.example.wspnew.activities.ManagerActivity;
import com.example.wspnew.classes.Comment;
import com.example.wspnew.classes.News;

public class updateNews extends Thread {
    Comment comment;
    News news;
    public updateNews(Comment comment, News news) {
        this.comment = comment;
        this.news = news;
    }

    @Override
    public void run() {
        ManagerActivity.currentManager.deleteNews(news.getTitle(), news.getDescription());
        news.getComments().add(comment);
        ManagerActivity.currentManager.addNews(news);
    }
}
