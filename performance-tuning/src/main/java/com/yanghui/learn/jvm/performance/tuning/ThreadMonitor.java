package com.yanghui.learn.jvm.performance.tuning;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 使用jvisualvm
 * 安装插件
 * 查看GC信息，生成堆快照（heap dump)
 * 线程信息/线程死锁
 * profiler分析程序性能
 *
 * @author YangHui
 */
public class ThreadMonitor {

    /**
     * VM参数：
     * -Xverify:none
     * @param args
     * @throws Exception
     */
    public static void main(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        createLockThread(new Object());
        br.read();
        deadLock();
    }

    public static void createBusyThread(){
        Thread thread = new Thread(()->{
            while (true) ;
        }, "testBusyThread");
        thread.start();
    }

    public static void createLockThread(final Object lock){
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "testLockThread");
        thread.start();
    }

    public static void deadLock(){
        //持有Integer.valueOf(1)对象的监视器锁，等待获取Integer.valueOf(2)对象的监视器锁
        new Thread(new DoubleLockRunnable(1,2)).start();
        //持有Integer.valueOf(2)对象的监视器锁，等待获取Integer.valueOf(1)对象的监视器锁
        new Thread(new DoubleLockRunnable(2,1)).start();
    }

    static class DoubleLockRunnable implements Runnable{

        int a;

        int b;

        DoubleLockRunnable(int a, int b){
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            //利用Integer.valueOf()使用了IntegerCache的特点,默认情况下传入-128-127之间的值会导致死锁
            synchronized (Integer.valueOf(a)){
                //等待另一个线程执行外层同步块
                LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
                synchronized (Integer.valueOf(b)){
                    System.out.println(a + b);
                }
            }
        }
    }
}
