package com.example.wspnew.classes;

public class Mark {
    private Course course;
    private float att1, att2, finalMark;

    public Mark(Course course, float att1, float att2, float finalMark) {
        this.course = course;
        this.att1 = att1;
        this.att2 = att2;
        this.finalMark = finalMark;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public float getAtt1() {
        return att1;
    }

    public void setAtt1(float att1) {
        this.att1 = att1;
    }

    public float getAtt2() {
        return att2;
    }

    public void setAtt2(float att2) {
        this.att2 = att2;
    }

    public float getFinalMark() {
        return finalMark;
    }

    public void setFinalMark(float finalMark) {
        this.finalMark = finalMark;
    }
}
