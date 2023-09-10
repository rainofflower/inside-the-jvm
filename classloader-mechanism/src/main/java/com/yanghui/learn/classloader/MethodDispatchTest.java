package com.yanghui.learn.classloader;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * 使用java.lang.invoke实现调用祖父类的方法
 */
public class MethodDispatchTest {

    static class GrandFather{
        void thinking(){
            System.out.println("i am GrandFather");
        }
    }

    static class Father extends GrandFather{
        void thinking(){
            System.out.println("i am Father");
        }
    }

    static class Son extends Father{
        void thinking(){
//            System.out.println("i am Son");
            //super关键字可以调用父类的方法，但是不能调用祖父类方法
//            super.thinking();
//            new GrandFather().thinking();
//            try {
//                MethodHandle methodHandle = MethodHandles.lookup().findSpecial(GrandFather.class, "thinking", MethodType.methodType(void.class), getClass());
//                methodHandle.invoke(this);
//            }catch (Throwable e){
//                  e.printStackTrace();
//            }
            try {
                MethodType methodType = MethodType.methodType(void.class);
                Field field = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                field.setAccessible(true);
                MethodHandles.Lookup implLookup = (MethodHandles.Lookup)field.get(null);
                MethodHandle methodHandle = implLookup.findSpecial(GrandFather.class, "thinking", methodType, GrandFather.class);
                methodHandle.invoke(this);
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Son son = new Son();
        son.thinking();
    }
}
