package com.yanghui.learn.tomcat.classloader;


import java.io.File;
import java.io.FileInputStream;

public class CommonClassLoader extends ClassLoader {

    private String classPath;

    public CommonClassLoader() {
        super(ClassLoader.getSystemClassLoader());
        this.classPath = "/Users/yanghui/project/inside-the-jvm/tomcat-classloader/common";
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
        try (FileInputStream is = new FileInputStream(classPath + File.separator + fileName)){
            byte[] b = new byte[is.available()];
            is.read(b);
            return defineClass(name, b, 0, b.length);
        } catch (Exception e) {
            throw new ClassNotFoundException(e.getMessage());
        }
    }
}
