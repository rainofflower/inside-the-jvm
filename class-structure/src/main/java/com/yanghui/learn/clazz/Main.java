package com.yanghui.learn.clazz;

/**
 *
 * @author yanghui
 * @date 2020-05-19 16:43
 **/
public class Main {

    public static void main(String... a){
        TestClass testClass = new TestClass();
        System.out.println(testClass.inc());
        TestClass2 testClass2 = new TestClass2();
        System.out.println(testClass2.inc());
        TestClass3 testClass3 = new TestClass3<Integer>();
        System.out.println(testClass3.str(5));
        TestClass4 testClass4 = new TestClass4<Integer>();
        System.out.println(testClass4.inc(5));

        TestClass5 testClass5 = new TestClass5<Integer>();
        System.out.println(testClass5.inc(5));

    }
}
