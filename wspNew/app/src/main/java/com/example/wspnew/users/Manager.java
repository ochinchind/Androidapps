package com.example.wspnew.users;

import com.example.wspnew.classes.Course;
import com.example.wspnew.classes.News;
import com.example.wspnew.enums.Gender;
import com.example.wspnew.utils.NewsAddData;
import com.example.wspnew.utils.addData;
import com.example.wspnew.utils.deleteNews;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Manager extends Employee {
    public Manager(String usertype, String firstName, String lastname, String login, String password, Gender gender, String id, float salary) {
        super(usertype, firstName, lastname, login, password, gender, id, salary);
    }
    public void addNews(News news) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(news);
            new NewsAddData(json).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void approveRegistration() {

    }

    public void assignCourseToTeacher() {

    }

    public void deleteNews(String title, String desc) {
        new deleteNews(title, desc).start();
    }
    public void updateNews() {

    }
    public void viewRequestsFromEmployees() {

    }

    public void addCourse(Teacher teacher, Course course) {
        teacher.getTeaches().add(course);
    }

    public void addCourse(Student student, Course course) {
        student.getCourses().add(course);
    }
}
