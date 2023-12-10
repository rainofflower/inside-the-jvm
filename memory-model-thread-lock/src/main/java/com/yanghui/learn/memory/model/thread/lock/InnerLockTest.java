package com.yanghui.learn.memory.model.thread.lock;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 *
 */
public class InnerLockTest {

    public static void main(String[] args) {
        System.out.println(VM.current().details());
        ObjectLock objectLock=new ObjectLock();
        System.out.println("object status:");
        objectLock.printSelf();
        System.out.println("================");
        System.out.println("class status:");
        String printable = ClassLayout.parseInstance(ObjectLock.class).toPrintable();
        //输出对象布局
        System.out.println("class = " + printable);
    }
}

