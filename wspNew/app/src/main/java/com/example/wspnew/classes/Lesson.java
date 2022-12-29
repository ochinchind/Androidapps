package com.example.wspnew.classes;

import com.example.wspnew.enums.LessonType;
import com.example.wspnew.enums.WeekDay;
import com.example.wspnew.users.Student;
import com.example.wspnew.users.Teacher;

import java.util.ArrayList;

public class Lesson {
    private Course course;
    private WeekDay weekday;
    private String time;
    private LessonType lessonType;
    private Teacher teacher;
    private String auditory;
    private ArrayList<Student> students;

    public Lesson(Course course, WeekDay weekday, String time, LessonType lessonType, Teacher teacher, String auditory) {
        this.course = course;
        this.weekday = weekday;
        this.time = time;
        this.lessonType = lessonType;
        this.teacher = teacher;
        this.auditory = auditory;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public WeekDay getWeekday() {
        return weekday;
    }

    public void setWeekday(WeekDay weekday) {
        this.weekday = weekday;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonType lessonType) {
        this.lessonType = lessonType;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getAuditory() {
        return auditory;
    }

    public void setAuditory(String auditory) {
        this.auditory = auditory;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
