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

    public static void main3(String[] args) {
        int a;
        //a未初始化，直接使用编译器会报 Variable 'a' might not have been initialized
        //System.out.println(a);
    }

    public int calc(){
        int a = 100;
        int b = 200;
        int c = 300;
        return (a + b) * c;
    }

    /**
     * 返回300
     * jdk1.4.2之前的javac编译器采用jsr和ret指令实现finally,
     * 1.4.2之后改为编译器在每段分支之后都将finally语句块中的内容冗余生成一遍来实现
     *
     * 以下为该方法字节码
     *  0 bipush 100
     *  2 istore_1
     *  3 iload_1       //第一个return开始： 将第二个本地变量（a）推送到栈顶 （本地变量的下标从0开始，0为第1个，次数的iload1为第二个）
     *  4 istore_2      //将栈顶值（a的值=100）出栈并存入到第三个本地变量。可以发现该return没有推出当前方法，而是将要返回的值存入到本地变量表
     *  5 sipush 300    //finally语句块 开始
     *  8 istore_1
     *  9 iload_1
     * 10 ireturn       //正常返回300 finally语句块 结束
     * 11 astore_2      //异常分支1-将异常对象的引用出栈并存入到第三个本地变量
     * 12 sipush 200
     * 15 istore_1
     * 16 iload_1       //第二个return开始： 将第二个本地变量（a）推送到栈顶
     * 17 istore_3      //栈顶出栈，存入第4个本地变量
     * 18 sipush 300    //finally语句块
     * 21 istore_1
     * 22 iload_1       //将第二个本地变量（a）推送到栈顶
     * 23 ireturn       //返回栈顶的值
     * 24 astore 4      //异常分支2
     * 26 sipush 300
     * 29 istore_1
     * 30 iload_1
     * 31 ireturn
     *
     * 《java虚拟机规范》明确要求java语言的编译器应当选择使用异常表而不是通过跳转指令来实现java异常及finally处理机制
     * 异常表
     * Exception table:
     * 0    5   11
     * 0    5   24
     * 11   18  24
     * 24   26  24
     */
    public int calc2(){
        int a;
        try {
            a = 100;
            return a;
        }catch (Exception e){
            a = 200;
            return a;
        }finally {
            a = 300;
            return a;
        }
    }

    /**
     * 返回100
     * 以下为该方法字节码
     *  0 bipush 100
     *  2 istore_1
     *  3 iload_1       //第一个return : 取出第2个本地变量（值为 100）
     *  4 istore_2      //存入第3个本地变量 100
     *  5 sipush 300    //finally
     *  8 istore_1      //将300存入第2个本地变量
     *  9 iload_2       //将第3个本地变量推送至栈顶（值为100）
     * 10 ireturn       //栈顶值返回（100）。可以看到finally语句块对变量a的赋值只是存入了第2个本地变量，最终返回的还是第3个本地变量（上一个return语句存入的变量槽）
     * 11 astore_2
     * 12 sipush 200
     * 15 istore_1
     * 16 iload_1
     * 17 istore_3
     * 18 sipush 300
     * 21 istore_1
     * 22 iload_3
     * 23 ireturn
     * 24 astore 4
     * 26 sipush 300
     * 29 istore_1
     * 30 aload 4
     * 32 athrow
     */

    public int calc3(){
        int a;
        try {
            a = 100;
            return a;
        }catch (Exception e){
            a = 200;
            return a;
        }finally {
            a = 300;
        }
    }

    /**
     * 返回200
     * @return
     */
    public int calc4(){
        int a;
        try {
            a = 100;
            int b = a / 0;
            return a;
        }catch (Exception e){
            a = 200;
            return a;
        }finally {
            a = 300;
        }
    }

    public static void main(String[] args) {
        ExecutionEngine executionEngine = new ExecutionEngine();
        executionEngine.calc();
        System.out.println(executionEngine.calc2());
        System.out.println(executionEngine.calc3());
        System.out.println(executionEngine.calc4());

    }
}
