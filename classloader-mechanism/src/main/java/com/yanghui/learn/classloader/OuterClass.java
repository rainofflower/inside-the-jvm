package com.yanghui.learn.classloader;

/**
 * 非静态内部类
 * javap -c OuterClass.class
 * 可以看到编译器自动添加了两个静态方法
 * static java.lang.String access$000(com.yanghui.learn.classloader.OuterClass);
 * static java.lang.String access$002(com.yanghui.learn.classloader.OuterClass, java.lang.String);
 * 分别用于内部类读写外部类实例的私有字段name
 *
 * javap -p OuterClass\$InnerClass.class
 * 可以看到编译器自动给内部类添加了final OuterClass this$0;属性
 * final com.yanghui.learn.classloader.OuterClass this$0;
 * 并且自动给构造方法加上了一个外部类类型的参数
 * public com.yanghui.learn.classloader.OuterClass$InnerClass(com.yanghui.learn.classloader.OuterClass);
 *
 */
public class OuterClass {

    private String name;

    public OuterClass(String name){
        this.name = name;
        System.out.println("outclass instance init, name = "+name);
    }

    public String getName() {
        return name;
    }

    class InnerClass{

        public InnerClass(){
            System.out.println("innerclass instance init, name = "+name);
        }

        public void print() {
            /**
             * 15 aload_0
             * 16 getfield #1 <com/yanghui/learn/classloader/OuterClass$InnerClass.this$0 : Lcom/yanghui/learn/classloader/OuterClass;>
             * 19 invokestatic #8 <com/yanghui/learn/classloader/OuterClass.access$000 : (Lcom/yanghui/learn/classloader/OuterClass;)Ljava/lang/String;>
             * name的获取通过this$0实例调用access$000方法获取
             */
            System.out.println("name = "+name);
            /**
             * 31 aload_0
             * 32 getfield #1 <com/yanghui/learn/classloader/OuterClass$InnerClass.this$0 : Lcom/yanghui/learn/classloader/OuterClass;>
             * 35 ldc #12 <update name>
             * 37 invokestatic #13 <com/yanghui/learn/classloader/OuterClass.access$002 : (Lcom/yanghui/learn/classloader/OuterClass;Ljava/lang/String;)Ljava/lang/String;>
             * 注意37，调用access$002方法设置this$0实例name的值
             */
            name = "update name";
            System.out.println("name = "+name);
        }

    }


    public static void main(String[] args) {
        OuterClass outer = new OuterClass("outer");
        InnerClass inner = outer.new InnerClass();
        inner.print();
        System.out.println("name = "+outer.getName());
    }
}
