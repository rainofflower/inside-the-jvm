package com.yanghui.learn.gc;

/**
 * @author YangHui
 */
public class AllocationTest {

    private static final int _1M = 1024 * 1024;

    public static void main(String... args){
        testAllocation1();
    }

    /**
     * VM参数：
     * -XX:+UseSerialGC -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * 去掉-XX:+UseSerialGC 可以发现java1.8默认使用Parallel Scavenge + Parallel Old收集器组合
     */
    public static void testAllocation1(){
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1M];
        allocation2 = new byte[2 * _1M];
        allocation3 = new byte[2 * _1M];
        allocation4 = new byte[4 * _1M]; //出现一次Minor GC
    }
}
