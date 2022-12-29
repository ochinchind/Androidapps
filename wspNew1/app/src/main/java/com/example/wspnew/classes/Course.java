package com.example.wspnew.classes;

import com.example.wspnew.enums.Faculty;

import java.util.Vector;

public class Course {
    private String id;
    private Faculty faculty;
    private int credits;
    private String title;

    public Course(String id, Faculty faculty, int credits, String title) {
        this.id = id;
        this.faculty = faculty;
        this.credits = credits;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", faculty=" + faculty +
                ", credits=" + credits +
                ", title='" + title + '\'' +
                '}';
    }
}