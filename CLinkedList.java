package Course_Management_System;

import java.io.IOException;

public interface CLinkedList {

//    判空
    boolean isEmpty();
//    增加
    void add(Course element);
//    删除
    void delete(String CourseID);
//    修改
    void modify(String CollegeID);
//    查找
    void select();

//    遍历
    void ergodic();

    //清空
    void clear();

    //导入
    void read() throws IOException;
}
