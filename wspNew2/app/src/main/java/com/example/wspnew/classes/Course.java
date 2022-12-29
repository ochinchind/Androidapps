package com.example.wspnew.classes;

import com.example.wspnew.enums.Faculty;

import java.util.Vector;

public class Course {
    int credits;
    String title;
    String id;
    Faculty faculty;
    public Course(int credits, String title, String id, Faculty faculty) {
        this.credits = credits;
        this.title = title;
        this.id = id;
        this.faculty = faculty;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Course other = (Course) obj;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Course [credits=" + credits + ", title=" + title + "]";
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
}