package com.yanghui.learn.classloader;

/**
 * 初始化阶段是执行类构造器<clinit>()方法的过程。
 * 在准备阶段，变量已经赋过一次系统要求的初始值，而在初始化阶段，则根据程序员制定的参数值去初始化类变量和其他资源。
 *
 * 类构造器<clinit>()方法：是由编译器自动收集类中的所有类变量的赋值动作和静态代码块中的语句合并产生的。
 *
 * 编译器收集的顺序是由语句在源文件中出现的顺序决定的；
 * 静态代码块只能访问定义在静态块之前的变量，定义在它之后的变量，在前面的静态块中可以赋值，但不能访问。
 *
 * <init>是 【对象构造器方法】，也就是说在程序执行 new 一个对象调用该对象的 constructor 方法时才会执行init方法，
 * 而<clinit>是 【类构造器方法】，
 * 也就是在jvm进行 类加载—–检验—-准备——解析—–初始化，中的初始化阶段jvm调用<clinit>方法。
 *
 * 虚拟机会保证在子类的<clinit>（）方法执行之前，父类的<clinit>（）方法已经执行完毕。
 * 因此在虚拟机中第一个被执行的<clinit>（）方法的类肯定是java.lang.Object。
 * 由于父类的<clinit>（）方法先执行，也就意味着父类中定义的静态语句块要优先于子类的变量赋值操作
 *
 * 虚拟机会保证一个类的<clinit>()方法在多线程中被正确的加锁、同步，如果多个线程去初始化一个类，那么只会有一个线程去执行类的<clinit>()方法，其他线程都处于等待状态。
 * 如果在一个类的<clinit>()方法中有耗时很长的操作，那就可能造成多个线程阻塞，在实际应用中这种阻塞往往是很隐蔽的。
 * @author YangHui
 */
public class Foo {

    public static final int A = 2;  //static final（final类变量）准备阶段就会完成内存分配与初始化，同时还会赋上值

    // <clinit>
    public static int b = 1;        //static（类变量） 准备阶段完成内存分配，初始化，设置初始值（此处为int类型，初始值为0）

    // <clinit>
    static{
        b = 3;
        c = 3;   //可以编译通过
        //b = c; //编译器提示非法向前引用
    }

    // <clinit>
    public static int c;

    // <init>
    int a;

    // <init>
    public Foo(){
        a = 3;
        b = 0;
    }

    static class SubFoo extends Foo{
        static int s = b;   // <clinit> 类构造方法执行，并且虚拟机会保证子类执行之前，父类的<clinit>先执行完成
    }

    public static void main(String... args){
        String s = "hi";
        System.out.println(s.toString());
        System.out.println(SubFoo.s);   // 打印结果为3
    }


}
