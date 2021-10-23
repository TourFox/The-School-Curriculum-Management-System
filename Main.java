package Course_Management_System;

public class Main {

    public static void main(String[] args)
    {
        //显示菜单
        Menu m = new Menu();  m.menu();

        Service s = new Service();  s.login();

    }
}
