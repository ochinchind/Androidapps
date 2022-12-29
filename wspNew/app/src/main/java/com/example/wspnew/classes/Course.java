package com.example.wspnew.classes;

import java.util.Vector;

public class Course {
    int credits;
    String title;
    Vector<Course> prerequesites = new Vector<Course>();
    public Course(int credits, String title, Vector<Course> prerequesites) {
        this.credits = credits;
        this.title = title;
        this.prerequesites = prerequesites;
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
    public Vector<Course> getPrerequesites() {
        return prerequesites;
    }
    public void setPrerequesites(Vector<Course> prerequesites) {
        this.prerequesites = prerequesites;
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
    public String vectorToString() {
        String vectorString = "";
        for(int i = 0; i < prerequesites.size(); i++) {
            vectorString += "\t" + prerequesites.get(i).toString() + "\n";
        }
        return vectorString;
    }
    @Override
    public String toString() {
        return "Course [credits=" + credits + ", title=" + title + ", \nprerequesites=" + vectorToString() + "]";
    }
}