package com.yanghui.learn.jvm.performance.tuning;

import java.util.ArrayList;

/**
 * jconsole 内存页签
 *
 * @author YangHui
 */
public class MonitoringTest {

    static class OOMObject{
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException{
        ArrayList<OOMObject> list = new ArrayList<>();
        for(int i = 0; i<num; i++){
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        /**
         * 在此处触发full gc由于仍在fillHeap方法内，GC Roots中包含当前方法栈帧本地变量list引用的对象（list对象处在作用域内），因此list不会被回收
         */
//        System.gc();
    }

    /**
     * VM参数：
     * -Xmx100m -Xms100m -XX:+UseSerialGC -XX:+PrintGCDetails
     * @param args
     * @throws Exception
     */
    public static void main(String... args) throws Exception {
        fillHeap(1000); //1000个约64KB的对象，约64MB，未超过VM堆空间参数100MB
        /**
         * 在fillHeap方法栈帧出栈之后触发full gc，从GC Roots到fillHeap方法里的局部变量list引用的数组已经不可达了（list对象不在当前作用域内了），所以list会被回收
         */
        System.gc();
        fillHeap(1000);
        Thread.sleep(5000);
        fillHeap(1000);
        Thread.sleep(5000);
        System.out.println("程序退出");
    }

}
