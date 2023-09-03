package com.yanghui.learn.classloader;

public class FieldResolution {

    interface Interface0{
        int A = 0;
    }

    interface Interface1 extends Interface0{
        int A = 1;
    }

    interface Interface2{
        int A = 2;
    }

    static class Parent implements Interface1{
        public static int A = 3;
    }

    static class Sub extends Parent implements Interface2{
        //如果注释下面这行代码，接口和父类同时存在字段A，main方法Sub.A那行代码 编译器提示 Reference to 'A' is ambiguous, both 'Parent.A' and 'Interface2.A' match
        public static int A = 4;
    }

    public static void main(String[] args) {
        System.out.println(Sub.A);
        System.out.println(new P().i);
        System.out.println(new C().i);
    }


    static class P{
        int i = 0;

        //java语言不支持属性重载（不允许 简单名称相同- 特征描述符不同 ）
//        long i = 0;
    }

    static class C extends P{
        int i = 1;
    }


}
