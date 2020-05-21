package com.yanghui.learn.clazz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yanghui
 * @date 2020-05-20
 **/
public class TestClass3<T> implements Serializable {

    private int m;

    private int n = 66;

    static final int a = 2;

    static final String A = "A";

    static String b = "B";

    static final List EMPTY_LIST = Collections.emptyList();

    static String c = "A";

    static final String d = "A";

    private final String S = "--constant--";

    private transient volatile T t;

    public <V> List<T> str(T item, V param){
        List<T> list = new ArrayList<>();
        list.add(item);
        return list;
    }

    public <V> V str(V param){
        if(param.getClass() == Integer.class || param.getClass() == int.class){
            Integer v = ((Integer)param).intValue()+1;
            return (V)v;
        }
        return param;
    }

    public final int inc(){
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
