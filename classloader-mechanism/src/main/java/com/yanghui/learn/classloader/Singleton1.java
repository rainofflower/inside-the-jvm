package com.yanghui.learn.classloader;

public class Singleton1 {

    private Singleton1(){}

    private static volatile Singleton1 instance;

    public static Singleton1 getInstance() {
        if(instance == null){
            synchronized (Singleton1.class){
                if(instance == null){
                    instance = new Singleton1();
                }
            }
        }
        return instance;
    }
}
