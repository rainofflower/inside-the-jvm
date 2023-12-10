package com.yanghui.learn.memory.model.thread.lock;

import org.openjdk.jol.info.ClassLayout;

public class ObjectLock {

    private static boolean sb = false;

    private static Boolean sB = true;

    private boolean a;

    private byte b;

    private char c;

    private short s;

    private int i;

    private float f;

    private double d;

    private long l;

    private Integer amount = 0; //整型字段占用4字节

    private Long L = 0L;

    public void increase() {
        synchronized (this) {
            amount++;
        }
    }

    /**
     * 输出十六进制、小端模式的hashCode
     */
    public String hexHash() {
        //对象的原始 hashCode，Java默认为大端模式
        int hashCode = this.hashCode();
        //转成小端模式的字节数组
        byte[] hashCode_LE = ByteUtil.int2Bytes_LE(hashCode);
        //转成十六进制形式的字符串
        return ByteUtil.byteToHex(hashCode_LE);
    }

    /**
     * 输出二进制、小端模式的hashCode
     */
    public String binaryHash() {
        //对象的原始hashCode，Java默认为大端模式
        int hashCode = this.hashCode();
        //转成小端模式的字节数组
        byte[] hashCode_LE = ByteUtil.int2Bytes_LE(hashCode);
        StringBuffer buffer = new StringBuffer();
        for (byte b : hashCode_LE) {
            //转成二进制形式的字符串
            buffer.append(ByteUtil.byte2BinaryString(b));
            buffer.append(" ");
        }
        return buffer.toString();
    }

    /**
     * 输出十六进制、小端模式的ThreadId
     */
    public String hexThreadId() {
        //当前线程的threadID，Java默认为大端模式
        long threadID = Thread.currentThread().getId();
        //转成小端模式的字节数组
        byte[] threadID_LE = ByteUtil.long2bytes_LE(threadID);
        //转成十六进制形式的字符串
        return ByteUtil.byteToHex(threadID_LE);
    }

    /**
     * 输出二进制、小端模式的ThreadId
     */
    public String binaryThreadId() {
        //当前线程的threadID，Java默认为大端模式
        long threadID = Thread.currentThread().getId();
        byte[] threadID_LE = ByteUtil.long2bytes_LE(threadID);
        StringBuffer buffer = new StringBuffer();
        for (byte b : threadID_LE) {
            //转成二进制形式的字符串
            buffer.append(ByteUtil.byte2BinaryString(b));
            buffer.append(" ");
        }
        return buffer.toString();
    }

    public void printSelf() {
        // 输出十六进制、小端模式的hashCode
        System.out.println("lock hexHash= " + hexHash());
        // 输出二进制、小端模式的hashCode
        System.out.println("lock binaryHash= " + binaryHash());
        //通过JOL工具获取this的对象布局
        String printable = ClassLayout.parseInstance(this).toPrintable();
        //输出对象布局
        System.out.println("lock = " + printable);
    }

}
