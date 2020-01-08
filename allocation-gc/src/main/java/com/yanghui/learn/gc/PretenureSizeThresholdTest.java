package com.yanghui.learn.gc;

/**
 * @author YangHui
 */
public class PretenureSizeThresholdTest {

    private static final int _1M = 1024 * 1024;

    public static void main(String... args){
        testPretenureSizeThreshold();
    }

    /**
     * VM参数：
     * -XX:+UseSerialGC -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
     * 大于3M的对象直接在老年代分配
     */
    public static void testPretenureSizeThreshold(){
        byte[] allocation;
        allocation = new byte[4 *  _1M]; //直接分配在老年代
    }
}
