package com.yanghui.learn.gc;

/**
 * @author YangHui
 */
public class TenuringThresholdTest {

    private static final int _1M = 1024 * 1024;

    public static void main(String... args){
        testTenuringThreshold();
    }

    /**
     * VM参数：
     * -XX:+UseSerialGC -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
     * -XX:TargetSurvivorRatio=90
     * 测试对象晋升年老代的年龄
     * 此处如果不增加调整-XX:TargetSurvivorRatio参数，会出现和《深入理解java虚拟机》p96页结果不一致，
     * 这是由于hotspot后面更新了SerialGC的实现，会动态计算age，然后取 Min(age,MaxTenuringThreshold)，具体可以参考Hotspot 6 update 21中SerialGC的实现，
     * 另外，书中还有关于动态年龄判定的。
     */
    public static void testTenuringThreshold(){
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1M / 5];
        allocation2 = new byte[4 * _1M];
        /**
         * 出现一次Minor GC（eden空间只有8m，没有足够空间分配allocation3，发起Minor GC），
         * allocation1仍然存活，并且能被survivor容纳，将被移动到survivor空间，对象年龄设置为1
         */
        allocation3 = new byte[4 * _1M];
        /**
         * allocation3 = null 导致引用与堆中的对象（此处为数组）断开连接，当前栈帧中引用的对象到堆中的这个对象不可达，因此会被垃圾收集器回收
         */
        allocation3 = null;
        /**
         * 再次出现一次Minor GC，allocation1年龄达到1，晋升到老年代，
         * allocation3之前引用指向的对象由于不可达了，被回收，
         * GC后，新生代变成 0KB，allocation3引用指向的新的对象直接分配到eden
         */
        allocation3 = new byte[4 * _1M];
    }

}
