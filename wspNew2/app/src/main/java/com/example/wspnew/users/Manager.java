package com.example.wspnew.users;

import com.example.wspnew.activities.AdminActivity;
import com.example.wspnew.classes.Course;
import com.example.wspnew.classes.News;
import com.example.wspnew.enums.Gender;
import com.example.wspnew.utils.NewsAddData;
import com.example.wspnew.utils.addCourse;
import com.example.wspnew.utils.addData;
import com.example.wspnew.utils.deleteData;
import com.example.wspnew.utils.deleteNews;
import com.example.wspnew.utils.updateData;
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

    public void assignCourseToTeacher(Teacher teacher, Course course) {
        teacher.getTeaches().add(course);
        new deleteData(teacher.getLogin(), teacher.getPassword()).start();
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(teacher);
            new addData(json).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void assignCourseToStudent(Student student, Course course) {
        student.getCourses().add(course);
        new deleteData(student.getLogin(), student.getPassword()).start();
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(student);
            new addData(json).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteNews(String title, String desc) {
        new deleteNews(title, desc).start();
    }
    public void addCourse(Course course) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(course);
            new addCourse(json).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
