package Course_Management_System;

import java.io.Serializable;

public class Node<Course> implements Serializable   {

    public Course data;             //数据域
    public Node<Course> next;       //指针域
    public Node<Course> previous;   //指针域


    public Node(Course data) {
        super();
        this.data = data;
    }

    public Node(Course data,Node next,Node previous) {
        super();
        this.data = data;
        this.previous = previous;
        this.next = next;
    }
}
