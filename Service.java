package Course_Management_System;

import java.io.IOException;
import java.util.Scanner;

public class Service {

    //执行开始
    public void login() {
        //输入要求
        Scanner sca = new Scanner(System.in);

        LinkedLists List = new LinkedLists();

        while(true){
            String n = sca.nextLine();  //避免报错，改为输入字符类型数据
            switch (n){
                case "1": System.out.println("输入成功");
                    //变量声名
                    String CourseID;
                    String CourseName;
                    int CollageID = 0;
                    String CollageName = null;
                    String CourseClass = null;
                    String m; String flag;

                    //数据输入
                    System.out.println("请输入课程代码：");
                    CourseID = sca.next();

                    System.out.println("请输入课程名称：");
                    CourseName = sca.next();

                    do{
                        System.out.printf("请选择开课学院：（1.数计学院 2.文传学院 3.外国语学院 4.社科学院）");
                        m = sca.next();
                        switch(m) {
                            case "1": CollageName = "数计学院"; CollageID = 1; break;
                            case "2": CollageName = "文传学院"; CollageID = 2; break;
                            case "3": CollageName = "外国语学院"; CollageID = 3; break;
                            case "4": CollageName = "社科学院"; CollageID = 4; break;
                            default: System.out.println("输入错误，请重新输入"); break;
                        }
                    }while(Integer.parseInt(m) > 4 || Integer.parseInt(m) < 1);  //避免输入出错而跳过，导致数据未录入

                    do{
                        System.out.printf("请输入课程类型：（1.理论课 2.实验课 3.实践课 4.其他）");
                        m = sca.next();
                        sca.nextLine();//吸收换行符
                        switch(m) {
                            case "1": CourseClass = "理论课"; break;
                            case "2": CourseClass = "实验课"; break;
                            case "3": CourseClass = "实践课"; break;
                            case "4": CourseClass = "其他"; break;
                            default: System.out.println("输入错误，请重新输入"); break;
                        }
                    }while(Integer.parseInt(m) > 4 || Integer.parseInt(m) < 1);

                    //数据加入链表
                    Course cou = new Course(CourseID,CourseName,CollageID,CollageName,CourseClass);
                    List.add(cou); break;

                case "2": System.out.println("输入成功");
                    System.out.println("请输入课程代码：");
                    flag = sca.nextLine();
                    List.delete(flag); break;

                case "3": System.out.println("输入成功");
                    System.out.println("输入课程代码：");
                    flag = sca.nextLine();
                    List.modify(flag); break;

                case "4": System.out.println("输入成功");
                    System.out.println("选择输入 1.课程代码查询" + " 2.课程名称查询" + " 3.开课学院名称查询");
                    List.select(); break;

                case "0": System.out.println("输入成功");
                    System.exit(-1); break;

                default: System.out.println("输入错误，请重新输入");
            }
        }
    }
}
