package com.yanghui.learn.classloader;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                //下方代码获取的is == null，后面会走父加载器（即应用程序类加载器）的loadClass方法
//                InputStream is = this.getResourceAsStream(fileName);
                //可以获取到is（实际是应用程序类加载器AppClassLoader继承父类URLClassLoader的getResourceAsStream方法返回的)
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null) {
                    return super.loadClass(name);
                }
                try {
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = myClassLoader.loadClass("com.yanghui.learn.classloader.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.yanghui.learn.classloader.ClassLoaderTest);
    }
}
