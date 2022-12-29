package com.example.wspnew.utils;

import com.example.wspnew.classes.Comment;
import com.example.wspnew.classes.News;
import com.example.wspnew.enums.Faculty;
import com.example.wspnew.enums.Gender;
import com.example.wspnew.enums.NewsType;
import com.example.wspnew.users.Admin;
import com.example.wspnew.users.Employee;
import com.example.wspnew.users.Manager;
import com.example.wspnew.users.Student;
import com.example.wspnew.users.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Getters extends Thread {
    public static News getNews(String title, String description) {
        try {
            for (int i = 0; i < Storage.news.length(); i++) {
                JSONObject news = Storage.news.getJSONObject(i);
                if(news.getString("title").equals(title) && news.getString("description").equals(description)) {
                    JSONObject author = news.getJSONObject("author");
                    Manager manager = getAuthor(author);

                    String newsTitle = news.getString("title");
                    String newsDesc = news.getString("description");
                    String date = news.getString("date");
                    NewsType newsType = NewsType.valueOf(news.getString("newsType"));
                    News newNews = new News(newsTitle, newsDesc, date, manager, newsType);

                    JSONArray comments = news.getJSONArray("comments");
                    for(int j = 0; j < comments.length(); j++) {
                        JSONObject comment = comments.getJSONObject(j);
                        User commentAuthor = getCommentAuthor(comment);
                        Comment curComment = new Comment(commentAuthor, comment.getString("date"), comment.getString("text"));
                        newNews.getComments().add(curComment);
                    }
                    return newNews;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    public static Student getStudent(JSONObject author){

    }
    public static Manager getAuthor(JSONObject author) throws JSONException {
        String usertype = author.getString("usertype");
        String firstName = author.getString("firstName");
        String lastName = author.getString("lastName");
        String login = author.getString("login");
        String password = author.getString("password");
        Gender gender = Gender.valueOf(author.getString("gender"));
        String id = author.getString("id");
        float salary = (float) author.getDouble("salary");
        return new Manager(usertype, firstName, lastName, login, password, gender, id, salary);
    }
    public static User getCommentAuthor(JSONObject comment) throws JSONException {
        JSONObject author = comment.getJSONObject("author");
        String usertype = author.getString("usertype");
        String userFirstName = author.getString("firstName");
        String userLastName = author.getString("lastName");
        String userLogin = author.getString("login");
        String userPassword = author.getString("password");
        Gender userGender = Gender.valueOf(author.getString("gender"));
        switch (usertype) {
            case "Admin":
                Admin admin = new Admin(usertype, userFirstName, userLastName, userLogin, userPassword, userGender);
                return admin;
            case "Student":
                int year = author.getInt("year");
                Faculty faculty = Faculty.valueOf(author.getString("faculty"));
                String id = author.getString("id");
                Student student = new Student(usertype, userFirstName, userLastName, userLogin, userPassword, userGender, year, faculty, id);
                return  student;
            default:
                String idEmployee = author.getString("id");
                float salary = (float)author.getDouble("salary");
                Employee employee = new Employee(usertype, userFirstName, userLastName, userLogin, userPassword, userGender, idEmployee, salary);
                return employee;
        }
    }
}
