package com.yanghui.learn.memory.model.thread.lock;

/**
 * ThreadLocal的英文字面翻译为“线程本地”，
 * 实际上ThreadLocal代表的是线程本地变量，可能将
 * 其命名为ThreadLocalVariable更加容易让人理解
 *
 * 使用场景：
 * 1、线程隔离：可以每个线程绑定一个用户会话信息、数据库连接、HTTP请求、Session等
 * 2、跨函数传递数据：避免通过参数传递数据带来的高耦合
 */
public class ThreadLocalUse {

    // session id，线程本地变量
    private static final ThreadLocal<String> sidLocal = new ThreadLocal<>();//ThreadLocal.withInitial(()->"1");

    public static String getSid(){
        return sidLocal.get();
    }

    public static void setSid(String sid){
        sidLocal.set(sid);
    }

    public static void main(String[] args) {
        String s = sidLocal.get();
        System.out.println(s);
    }
}
