package com.yanghui.learn.classloader;

class clintTest {

    static class DeadLoopClass {

        static {
            if (true) {
                System.out.println(Thread.currentThread().getName() + " init DeadLoopClass");
                while (true) {
                }
            }
        }
    }

    /**
     * new指令触发DeadLoopClass类的初始化，其中一个线程阻塞会另一个线程
     * 执行打印
     * Thread-1 start
     * Thread-0 start
     * Thread-1 init DeadLoopClass
     *
     * @param args
     */
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start");
                DeadLoopClass deadLoopClass = new DeadLoopClass();
                System.out.println(Thread.currentThread().getName() + " run over");
            }
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();
    }

}
