package com.yanghui.learn.tomcat;

import com.yanghui.learn.tomcat.classloader.CatalinaClassLoader;
import com.yanghui.learn.tomcat.classloader.CommonClassLoader;
import com.yanghui.learn.tomcat.classloader.WebAppClassLoader;

import java.lang.reflect.Method;
import java.util.HashMap;

public class Application {

    public static void main(String[] args) throws Exception{
//        Server server = new Server();
//        server.start();
        CommonClassLoader commonClassLoader = new CommonClassLoader();
//        loadClassAndUse(commonClassLoader);

        CatalinaClassLoader catalinaClassLoader = new CatalinaClassLoader(commonClassLoader);
        Class cc = loadClass(catalinaClassLoader);
        Object co = instanceUse(cc);
        WebAppClassLoader webAppClassLoader = new WebAppClassLoader(commonClassLoader);
        Class wc = loadClass(webAppClassLoader);
        Object wo = instanceUse(wc);
        System.out.println(co.getClass() == wo.getClass());
        System.out.println(co.getClass().isAssignableFrom(co.getClass()));
        System.out.println(co.getClass().isAssignableFrom(wo.getClass()));
        String s = new String("s");
        System.out.println(s.getClass() == String.class);
        System.out.println(s.getClass().isAssignableFrom(String.class));
        HashMap<Class, Object> classMap = new HashMap<>();
        classMap.put(cc, co);
        classMap.put(wc, wo);
        System.out.println(classMap);

        HashMap<String, Object> typeMap = new HashMap<>();
        typeMap.put(cc.getTypeName(), co);
        typeMap.put(wc.getTypeName(), wo);
        System.out.println(typeMap);
    }

    private static Class loadClass(ClassLoader classLoader) throws Exception {
        Class barClass = classLoader.loadClass("com.yanghui.learn.tomcat.server.Bar");
        return barClass;
    }

    private static Object instanceUse(Class clazz) throws Exception {
        Object obj = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("printField");
        method.invoke(obj);
        System.out.println("class:"+obj.getClass()+"; classloader:"+obj.getClass().getClassLoader());
        return obj;
    }
}
