package com.yanghui.learn.memory.model.thread.lock;

public class SynchronizedVisibility {

    static class A{
        int a;
        public void increment(){
            a++;
        }
    }

    static class B{
        int a;
        public void increment(){
            a++;
        }
    }

    static int threadCount = 20;

    /**
     * synchronized的可见性测试。
     * 测试得到的结果是实例a跟实例b的字段a的值都是20000000
     * @param args
     */
    public static void main(String[] args) {
        A aInstance = new A();
        B bInstance = new B();
        for(int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(() -> {
                synchronized (aInstance) { //改成 bInstance 或者 Object.class 结果都一样
                    for (int j = 0; j < 1000000; j++) {
                        aInstance.increment();
                        bInstance.increment();
                    }
                }
            });
            thread.start();
        }
        while (Thread.activeCount() > 1){
            Thread.yield();
        }
        /**
         * aInstance.a = 20000000
         * bInstance.a = 20000000
         */
        System.out.println("aInstance.a = " + aInstance.a);
        System.out.println("bInstance.a = " + bInstance.a);
    }
}
