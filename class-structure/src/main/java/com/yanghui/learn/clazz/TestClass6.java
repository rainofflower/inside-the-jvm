package com.yanghui.learn.clazz;

import java.util.ArrayList;

/**
 * @author YangHui
 */
public class TestClass6 {

    public void test1(){
        ArrayList<String> list = new ArrayList<>();
        list.add("aaa");
        String str = "|";
        String title = "|";
        for(String bean : list){
            str = str + title + bean;
        }
        System.out.println(str);
    }

    public void test2(){
        ArrayList<String> list = new ArrayList<>();
        list.add("aaa");
        String str = "|";
        String title = "|";
        StringBuilder builder = new StringBuilder();
        for(String bean : list){
            builder.append(str)
            .append(title)
            .append(bean);
        }
        System.out.println(builder.toString());
    }
}
