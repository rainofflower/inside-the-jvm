package com.yanghui.learn.classloader;

/**
 * @author YangHui
 */
public class Bar{

    public static final int A = 2;

    private int a = 1;

    private int b;


    // <clinit>方法的一部分，一个类只会生成一个<clinit>方法
    static{
        System.out.println(A);
    }

    /**
     * 每个构造方法都会生成一个<init>方法
     * 前端编译-字节码生成-代码收敛-保证按顺序进行
     *  0 aload_0
     *  1 invokespecial #1 <java/lang/Object.<init> : ()V>
     *  4 aload_0
     *  5 iconst_1
     *  6 putfield #2 <com/yanghui/learn/classloader/Bar.a : I>
     *  9 aload_0
     * 10 iconst_2
     * 11 putfield #2 <com/yanghui/learn/classloader/Bar.a : I>
     * 14 aload_0
     * 15 dup
     * 16 getfield #3 <com/yanghui/learn/classloader/Bar.b : I>
     * 19 iconst_1
     * 20 iadd
     * 21 putfield #3 <com/yanghui/learn/classloader/Bar.b : I>
     * 24 return
     * 先执行父类的实例构造器 Object::<init>
     * 然后初始化变量 将实例变量赋零值，然后根据变量定义代码再次赋值，a初始化为1，b为零值，int零值为0
     * 最后执行语句块（实例构造器的{}代码块）
     */
    // 其中一个<init>方法的一部分
    public Bar(){
        a = 2;
        //此处不会报错，虚拟机会为b分配内存，并初始化为0，所以此处实际是计算0+1
        b += 1;
    }

    /**
     * 生成的<init>方法字节码
     *  0 aload_0
     *  1 invokespecial #1 <java/lang/Object.<init> : ()V>
     *  4 aload_0
     *  5 iconst_1
     *  6 putfield #2 <com/yanghui/learn/classloader/Bar.a : I>
     *  9 aload_0
     * 10 iload_1
     * 11 putfield #2 <com/yanghui/learn/classloader/Bar.a : I>
     * 14 return
     */
    // 其中一个<init>方法的一部分
    public Bar(int a){
        this.a = a;
    }

    /**
     * 前端编译优化
     * 1、字符串的加操作会被替换为StringBuilder的append()操作
     * 2、"ab" + "c" + "d" 还会进行 常量折叠 的代码优化
     * 字节码
     *  0 new #4 <java/lang/StringBuilder>
     *  3 dup
     *  4 invokespecial #5 <java/lang/StringBuilder.<init> : ()V>
     *  7 aload_1
     *  8 invokevirtual #6 <java/lang/StringBuilder.append : (Ljava/lang/String;)Ljava/lang/StringBuilder;>
     * 11 ldc #7 <abcd>
     * 13 invokevirtual #6 <java/lang/StringBuilder.append : (Ljava/lang/String;)Ljava/lang/StringBuilder;>
     * 16 invokevirtual #8 <java/lang/StringBuilder.toString : ()Ljava/lang/String;>
     * 19 astore_1
     * 20 aload_1
     * 21 areturn
     */
    public String strOp(String str){
        str = str + "ab" + "c" + "d";
        return str;
    }

    /**
     *  0 new #4 <java/lang/StringBuilder>
     *  3 dup
     *  4 invokespecial #5 <java/lang/StringBuilder.<init> : ()V>
     *  7 aload_1
     *  8 invokevirtual #6 <java/lang/StringBuilder.append : (Ljava/lang/String;)Ljava/lang/StringBuilder;>
     * 11 ldc #9 <ab>
     * 13 invokevirtual #6 <java/lang/StringBuilder.append : (Ljava/lang/String;)Ljava/lang/StringBuilder;>
     * 16 invokevirtual #8 <java/lang/StringBuilder.toString : ()Ljava/lang/String;>
     * 19 astore_1
     * 20 aload_1
     * 21 areturn
     */
    public String strOp1(String str){
        str = str + "ab";
        return str;
    }

    public static void main(String[] args) {
        //加载bar类执行其<clinit>方法，打印2（static{}块）
        Bar bar = new Bar();
        //打印1
        System.out.println(bar.b);
    }
}
