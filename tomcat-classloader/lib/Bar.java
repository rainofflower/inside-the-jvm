package com.yanghui.learn.tomcat.server;

/**
 * @author YangHui
 */
public class Bar {

    public static final int A = 0;

    private int a = 0;

    static{
        System.out.println(A);
    }

    public Bar(){

    }

    public void printField(){
        System.out.println("static field A = " + A);
        System.out.println("instance field a = " + a);
    }
}
