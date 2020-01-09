package com.yanghui.learn.jvm.performance.tuning;

/**
 * jps -l
 * jstat -gc pid
 * jstat -gcutil pid
 * jmap -dump:format=b,file=F:\java\workspace-1\inside-the-jvm\performance-tuning\heapdump1.bin pid
 * jmap -heap pid
 * jmap -histo pid
 * jmap -clstats pid
 * jhat F:\java\workspace-1\inside-the-jvm\performance-tuning\heapdump1.bin
 * jstack -l pid
 *
 * -XX:+PrintGCDetails -Xms10m -Xmx10m -Xmn5m
 * @author YangHui
 */
public class MyTest1 {

    private static final int _1M = 1024 * 1024;

    public static void main(String... args){
        for(;;){
            byte[] allocation1;
            allocation1 = new byte[_1M * 2];
            System.out.println("hello jvm");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
