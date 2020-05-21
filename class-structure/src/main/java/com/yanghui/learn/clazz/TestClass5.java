package com.yanghui.learn.clazz;

import java.io.Serializable;

/**
 * @author yanghui
 * @date 2020-05-20
 **/
public class TestClass5<T> implements Serializable {

    private int m;

    private int n = 66;

    static final String b = "B";

    private transient volatile T t;

    public final int inc(){
        return m + 1;
    }

    public int inc(int i){
        return m + i;
    }

    public int dec(int i){
        return m - i;
    }

}
