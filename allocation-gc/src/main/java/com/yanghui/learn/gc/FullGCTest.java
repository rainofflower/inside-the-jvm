package com.yanghui.learn.gc;

/**
 * @author YangHui
 */
public class FullGCTest {

    private static final int _1M = 1024 * 1024;

    public static void main(String... args){
        fullGC();
    }

    /**
     * VM参数：
     * -XX:+UseSerialGC -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void fullGC(){
        byte[] allocation1,allocation2,allocation3,allocation4,allocation5,allocation6,allocation7,allocation8;
        allocation1 = new byte[_1M / 2];
        allocation2 = new byte[_1M * 4];
        allocation3 = new byte[_1M * 4];
        allocation2 = null;
        allocation4 = new byte[_1M * 4];
        allocation5 = new byte[_1M * 4];
        allocation4 = null;
        allocation6 = new byte[_1M * 2];
        allocation7 = new byte[_1M * 2];
        allocation7 = null;
        allocation7 = new byte[_1M * 2];
        allocation8 = new byte[_1M * 2];
    }
}
