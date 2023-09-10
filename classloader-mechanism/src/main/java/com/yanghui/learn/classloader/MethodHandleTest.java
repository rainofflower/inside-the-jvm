package com.yanghui.learn.classloader;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * JSR 292 MethodHandle基础用法
 */
public class MethodHandleTest {

    static class ClassA{
        public void println(String str){
            System.out.println(this.getClass() + " " + str);
        }

    }

    public static void main(String[] args) throws Throwable {
        Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
        //使用反射实现调用具体类型的方法
        //obj.getClass().getDeclaredMethod("println", String.class).invoke(obj, "dynamic method handle");
        //使用方法句柄实现动态确定目标方法
        getMethodHandle(obj).invokeExact("dynamic method handle");
    }

    public static MethodHandle getMethodHandle(Object receiver) throws NoSuchMethodException, IllegalAccessException {
        MethodType methodType = MethodType.methodType(void.class, String.class);
        return MethodHandles.lookup().findVirtual(receiver.getClass(), "println", methodType).bindTo(receiver);
    }
}
