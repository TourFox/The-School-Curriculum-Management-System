package Course_Management_System;

import java.io.Serializable;

public class Course implements Serializable {
    /*课程属性：
        课程代码 - 8位字符串
        课程名 - 最长为40的字符串
        开课学院（编号） - 两位
        开课学院名 - 最长为30位的字符串
        课程类别 - 最长为16位的字符串
    * */
    private String CourseID;     //课程代码
    private String CourseName;   //课程名
    private int CollegeID;       //开课学院（编号)
    private String CollegeName;  //开课学院名
    private String CourseClass;  //课程类别

    public Course() {
        this("00000000","XXXX",00,"XXXX","XXXX");
    }

    public Course(String CourseID,String CourseName,int CollegeID,String CollegeName,String CourseClass)
    {
        super();
        this.CourseID = CourseID;
        this.CourseName = CourseName;
        this.CollegeID = CollegeID;
        this.CollegeName = CollegeName;
        this.CourseClass = CourseClass;
    }

    public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(String Course) {
        this.CourseID = Course;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        this.CourseName = courseName;
    }

    public int getCollegeID() {
        return CollegeID;
    }

    public void setCollegeID(int collageID) {
        this.CollegeID = collageID;
    }

    public String getCollegeName() {
        return CollegeName;
    }

    public void setCollegeName(String collageName) {
        this.CollegeName = collageName;
    }

    public String getCourseClass() {
        return CourseClass;
    }

    public void setCourseClass(String courseClass) {
        this.CourseClass = courseClass;
    }

}

