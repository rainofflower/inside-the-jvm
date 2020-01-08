package com.yanghui.learn.classloader;

/**
 * @author YangHui
 */
public class Bar{

    public static final int A = 2;


    static{
        System.out.println(A);
    }

    public Bar(){

    }
}
