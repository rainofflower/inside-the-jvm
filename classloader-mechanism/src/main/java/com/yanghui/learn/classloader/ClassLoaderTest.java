package com.yanghui.learn.classloader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ClassLoaderTest {

    public static void main(String[] args) throws InterruptedException {
        Foo foo = new Foo();
        TimeUnit.MINUTES.sleep(10);
        System.out.println(Foo.SubFoo.s);
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        threadPool.shutdown();
    }
}
