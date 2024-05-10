package edu.neumont;

import java.util.ArrayList;

public class Faculty extends Human{
    protected int officeNum;
    protected String officeHours;
    private ArrayList<Course> taughtCourses = new ArrayList<>();
    protected boolean isFullTime;
    public Faculty(String name, int birthYear, int officeNum, String officeHours) {
        super(name, birthYear);
        this.officeNum = officeNum;
        this.officeHours = officeHours;
    }

    public int getOfficeNum() {
        return officeNum;
    }

    public void setOfficeNum(int officeNum) {
        this.officeNum = officeNum;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    protected void addCourse(Course course) {
        this.taughtCourses.add(course);
    }

    protected void remCourse(Course course) {
        for (int i = 0; i < this.taughtCourses.size(); i++) {
            if (this.taughtCourses.get(i).getName().equalsIgnoreCase(course.getName())) {
                this.taughtCourses.remove(i);
            }
        }
    }

    public boolean isFullTime() {
        return isFullTime;
    }

    public void setFullTime(boolean fullTime) {
        isFullTime = fullTime;
    }
}