package com.yanghui.learn.clazz;

/**
 * @author yanghui
 * @date 2020-05-19 16:40
 **/
public class TestClass {

    private int m;

    public strictfp int inc(){
        return m + 1;
    }

    public static int inc(int i){
        i++;
        return i;
    }
}
