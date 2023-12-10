package com.yanghui.learn.memory.model.thread.lock;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class ThreadPoolExecutorHookDemo {

    public static final ThreadLocal<Long> time = new ThreadLocal<>();

    static class Task implements Runnable{

        private String name;

        Task(String name){
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(this.name + "doing");
            LockSupport.parkNanos(1000*1000*1000*2);
            System.out.println(this.name +" 处理完成");
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,
                4,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(2)) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(r + "前钩子被执行");
                time.set(System.currentTimeMillis());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println(r + "后钩子被执行,任务执行时长(ms)：" + (System.currentTimeMillis() - time.get()));
            }

            @Override
            protected void terminated() {
                System.out.println("线程池已终止。");
            }
        };
        for(int i = 1; i<=5; i++){
            poolExecutor.execute(new Task("任务" + i));
        }
        LockSupport.parkNanos(1000*1000*1000*10);
        poolExecutor.shutdown();
    }
}
