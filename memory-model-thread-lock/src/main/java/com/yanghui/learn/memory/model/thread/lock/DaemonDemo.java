package com.yanghui.learn.memory.model.thread.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * 守护线程随着最后一个用户线程结束，进程退出而结束（JVM会强行终止所有守护线程的执行）
 * main线程也是一条用户线程
 */
public class DaemonDemo {

    static class DaemonThread extends Thread{

        public DaemonThread(){
            super();
            this.setDaemon(true);
        }

        @Override
        public void run() {
            for(;;){
                System.out.println(getName() + "是守护线程："+isDaemon());
                LockSupport.parkNanos(1000 * 1000 *1000* 2);
            }
        }
    }

    static class UserThread extends Thread{

        @Override
        public void run() {
            for(int i = 0; i < 5; i++){
                System.out.println(getName() + "是守护线程："+isDaemon());
                LockSupport.parkNanos(1000 * 1000 *1000 * 2);
            }
            System.out.println(getName() + "结束");
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "是守护线程："+Thread.currentThread().isDaemon());
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.start();
        UserThread userThread = new UserThread();
        userThread.start();
        System.out.println(Thread.currentThread().getName() + "结束");
    }
}
