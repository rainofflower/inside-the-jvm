package com.yanghui.learn.clazz;

/**
 * @author yanghui
 * @date 2020-05-20
 **/
public class TestClass2 {

    private int m;

    private final String S = "--constant--";

    public int inc(){
        return m + 1;
    }

    public int inc(int i){
        return m + i;
    }

    public int dec(int i){
        return m - i;
    }

    public Object handler() throws Exception{
        String temp = S.toUpperCase();
        return temp;
    }

}
