package com.yanghui.learn.classloader;

public class ExecutionEngine {

    /**
     * -XX:+PrintGCDetails
     * 或者
     * -verbose:gc
     * @param args
     */
    public static void main1(String[] args) {
        byte[] bytes = new byte[64 * 1024 * 1024];
        System.gc();
    }

    public static void main2(String[] args) {
        {
            byte[] bytes = new byte[64 * 1024 * 1024];
            //手动将变量设置为null,把变量对应的局部变量槽清空，下方的System.gc()也会回收上面的数组对象
            //bytes = null;
        }
        //加上跟不加上以下一行在测试时会出现不一样的垃圾回收结果。
        // 当虚拟机使用解释器执行字节码时会有代码已经离开变量作用域但是变量槽依旧存在对象的引用情况（变量槽还没有被其他变量复用）
        // 作为GC Roots一部分，这种关联没有被及时打断
        // int a = 0;
        System.gc();
    }

    public static void main(String[] args) {
        int a;
        //a未初始化，直接使用编译器会报 Variable 'a' might not have been initialized
        //System.out.println(a);
    }
}
