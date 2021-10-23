package Course_Management_System;

import java.io.*;
import java.util.Scanner;

public class LinkedLists implements CLinkedList {

    //声名头节点
    private Node first;
    private Node last;   //尾节点
    private BufferedReader reader;

    //判空
    @Override
    public boolean isEmpty() {
        return this.first == null;
    }

    //添加数据
    @Override
    public void add(Course element) {
        //从缓存中获取数据
        //先清空现有缓存链表
        clear();

        //数据域导入
        Node<Course> node = new Node(element);

        if(first == null) {
            node.previous = null;
            node.next = null;
            first = node;
            last = node;
        } else {
            //判断是否重复
            Node<Course> temp = first;

            while(temp != null) {
                //判断是否录入
                if(temp.data.getCourseID().equals(node.data.getCourseID())) {
                    System.out.println("此课程存在，不予录入。");
                    return ;
                }

                temp = temp.next;
            }

            //添加进链表里（接在前一个节点之后）
            node.previous = last;  //新节点与上一个节点建立连接
            node.next = null;

            last.next = node;  //上一个节点与新节点建立连接
            last = node;
        }

        //写入文件
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("E:\\java\\Course Management System\\src\\data",true);

            fos.write(node.data.getCourseID().getBytes());     fos.write(" ".getBytes());
            fos.write(node.data.getCourseName().getBytes());   fos.write(" ".getBytes());
            fos.write(node.data.getCourseID().getBytes());     fos.write(" ".getBytes());
            fos.write(node.data.getCollegeName().getBytes());  fos.write(" ".getBytes());
            fos.write(node.data.getCourseClass().getBytes());  fos.write("\r\n".getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //删除
    @Override
    public void delete(String CourseID) {
        Scanner sca = new Scanner(System.in);
        int flag = 0;

        //从缓存中获取数据
        //先清空现有缓存链表
        clear();

        //从文件中获取数据
        try {
            read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //再获取链表缓存
        Node<Course> temp = first;
        while(temp != null) {
            if(temp.data.getCourseID().equals(CourseID)) {
                System.out.printf("课程代码：%s\n",temp.data.getCourseID());
                System.out.printf("课程名称：%s\n",temp.data.getCourseName());
                System.out.printf("开课学院：%s\n",temp.data.getCollegeName());
                System.out.printf("课程类别：%s\n",temp.data.getCourseClass());
                flag = 1;  break;
            } else {
                temp = temp.next;
            }
        }

        if (isEmpty()) {
            System.out.println("目标课程为空。");
            return ;
        } else if(flag == 0) {
            System.out.println("无目标课程，请确认后重新输入。");
            return ;
        }

        //只通过课程代码来删除
        System.out.println("是否删除？ Y / N");
        String yn = sca.nextLine();
        switch (yn) {
            case "Y" -> {
                Node up = temp.previous;
                Node down = temp.next;

                if(up != null) {  //如果temp有前驱节点（不是第一个节点）
                    up.next = down;
                } else {
                    last = up;
                }

                if(down != null) {  //如果temp有后继节点（不是最后一个节点）
                    down.previous = up;  //使后继结点指向前驱节点
                } else {
                    first = down;
                }

                System.out.println("删除成功");
            }
            case "N" -> System.out.println("取消删除");
            default -> System.out.println("输入错误，请重新操作");
        }

        //删除后直接读入文件
        write();
    }

    //修改
    @Override
    public void modify(String CollegeID) {
        //从缓存中获取数据
        //先清空现有缓存链表
        clear();

        //从文件中获取数据
        try {
            read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner sca = new Scanner(System.in);
        Node<Course> temp = first;

        while(temp != null) {
            if(temp.data.getCourseID().equals(CollegeID)) {
                System.out.printf("课程代码：%s\n",temp.data.getCourseID());
                System.out.printf("课程名称：%s\n",temp.data.getCourseName());
                System.out.printf("开课学院：%s\n",temp.data.getCollegeName());
                System.out.printf("课程类别：%s\n",temp.data.getCourseClass());
                break;
            } else {
                temp = temp.next;
            }
        }

        if(temp == null) {  //循环遍历到链表末端
            System.out.println("找不到此课程，请重新输入");
            return ;
        } else {
            System.out.println("输入新的课程名：");
            String CourseName = sca.nextLine();
            temp.data.setCourseName(CourseName);
        }
        write();
    }

    //查找
    @Override
    public void select() {
        Scanner sca = new Scanner(System.in);
        int k = 0;
        System.out.println("请输入第几号：");
        int index = sca.nextInt();
        sca.nextLine(); //在这里单独写一句读取语句用于读取吸收换行符

        //从缓存中获取数据
        //先清空现有缓存链表
        clear();

        //从文件中获取数据
        try {
            read();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("请输入查询的依据信息：");
        String flag = sca.nextLine();

        Node<Course> temp = first;

        switch (index) {
            case 1:  //课程代码--这里只查询一次
                while(temp != null) {
                    if(temp.data.getCourseID().equals(flag)) {
                        System.out.printf("课程代码：%s\n",temp.data.getCourseID());
                        System.out.printf("课程名称：%s\n",temp.data.getCourseName());
                        System.out.printf("开课学院：%s\n",temp.data.getCollegeName());
                        System.out.printf("课程类别：%s\n",temp.data.getCourseClass());
                        k = 1;  break;
                    }
                    temp = temp.next;
                }
                if(k == 1) {
                    System.out.println("查找完成。");
                } else {
                    System.out.println("未查找到目标。");
                }
                break;

            case 2:  //课程名称
                while(temp != null) {
                    if(temp.data.getCourseName().equals(flag)) {
                        System.out.printf("课程代码：%s\n",temp.data.getCourseID());
                        System.out.printf("课程名称：%s\n",temp.data.getCourseName());
                        System.out.printf("开课学院：%s\n",temp.data.getCollegeName());
                        System.out.printf("课程类别：%s\n",temp.data.getCourseClass());
                        k = 1;
                    }
                    temp = temp.next;
                }
                if(k == 1) {
                    System.out.println("查找完成。");
                } else {
                    System.out.println("未查找到目标。");
                }
                break;

            case 3:  //开课学院名称
                while(temp != null) {
                    if(temp.data.getCollegeName().equals(flag)) {
                        System.out.printf("课程代码：%s\n",temp.data.getCourseID());
                        System.out.printf("课程名称：%s\n",temp.data.getCourseName());
                        System.out.printf("开课学院：%s\n",temp.data.getCollegeName());
                        System.out.printf("课程类别：%s\n",temp.data.getCourseClass());
                        k = 1;
                    }
                    temp = temp.next;
                }
                if(k == 1) {
                    System.out.println("查找完成。");
                } else {
                    System.out.println("未查找到目标。");
                }
                break;

            default: System.out.println("输入数据不在查询范围之内");
        }

    }

    //遍历
    @Override
    public void ergodic() {

        Node<Course> temp = null;

        temp = first;

        if(isEmpty()) {
            System.out.println("无可遍历课程");
            return ;
        }

        while(temp != null) {

            System.out.printf("课程代码：%s\n",temp.data.getCourseID());
            System.out.printf("课程名称：%s\n",temp.data.getCourseName());
            //开课学院编号
            System.out.printf("开课学院：%s\n",temp.data.getCollegeName());
            System.out.printf("课程类别：%s\n",temp.data.getCourseClass());

            temp = temp.next;
        }
    }

    //清空
    @Override
    public void clear() {
        Node<Course> temp = first;

        if(isEmpty()) {
//            System.out.println("内存中无数据内容。");
            return ;
        }
        temp.next = null;
        temp.previous = null;
    }

    //输入（文件-->内存）
    @Override
    public void read() throws IOException {

        //确定文件输入的位置
        String filepath = "E:\\java\\Course Management System\\src\\data";
        //获得全部文件--字符输出流
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        //文件内容赋给字符串temp
        String temp = reader.readLine();

        while (temp != null) {
            // 解析字符串
            String[] data = temp.split(" ");

            String CourseID = data[0];     //课程代码
            String CourseName = data[1];   //课程名
            int CollegeID = Integer.parseInt(data[2]);       //开课学院（编号)
            String CollegeName = data[3];  //开课学院名
            String CourseClass = data[4];  //课程类别

            Course course = new Course(CourseID,CourseName,CollegeID,CollegeName,CourseClass);
//            add(course);  //添加到缓存

            Node<Course> node = new Node<>(course);

            if(first == null) {
                node.previous = null;
                node.next = null;
                first = node;
                last = node;
            } else {
                //添加进链表里（接在前一个节点之后）
                node.previous = last;  //新节点与上一个节点建立连接
                node.next = null;

                last.next = node;  //上一个节点与新节点建立连接
                last = node;
            }

            temp = reader.readLine();
        }

        //关闭输出流
        reader.close();
    }

    //输出文件（内存-->文件）
    public void write() {
        Scanner sca = new Scanner(System.in);
        Node<Course> temp = null;

        temp = first;

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream("E:\\java\\Course Management System\\src\\data");

            while(temp != null) {

                fos.write(temp.data.getCourseID().getBytes());     fos.write(" ".getBytes());
                fos.write(temp.data.getCourseName().getBytes());   fos.write(" ".getBytes());
                fos.write(String.valueOf(temp.data.getCollegeID()).getBytes());     fos.write(" ".getBytes());
                fos.write(temp.data.getCollegeName().getBytes());  fos.write(" ".getBytes());
                fos.write(temp.data.getCourseClass().getBytes());  fos.write("\r\n".getBytes());

                temp = temp.next;
            }

//            fos.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
