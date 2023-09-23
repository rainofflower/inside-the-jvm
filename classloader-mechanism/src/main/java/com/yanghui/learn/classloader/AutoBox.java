package com.yanghui.learn.classloader;

public class AutoBox {

    public static void main(String[] args) {
        /**
         *  0 iconst_1
         *  1 invokestatic #2 <java/lang/Integer.valueOf : (I)Ljava/lang/Integer;>  //自动装箱（调用Integer.valueOf方法）
         *
         */
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        /**
         *  true
         *  48 aload_3      //将第四个引用类型本地变量（即 Integer c）推送至栈顶
         *  49 aload 4      //将第五个引用类型本地变量（即 Integer d）推送至栈顶
         *  51 if_acmpne 58 (+7)       //比较栈顶两引用类型数值，当结果不相等时跳转 （跳转到 58 iconst_0） 由于c跟d的值是3，在java中都对应同一个Integer缓存对象，所以此处相等
         *  54 iconst_1     //未跳转时执行，将int型1推送至栈顶，对应boolean的true (51不会跳转，故会执行此处)
         *  55 goto 59 (+4) //无条件跳转 （跳转到59）
         *  58 iconst_0     //51跳转执行，对应boolean的false
         *  59 invokevirtual #7 <java/io/PrintStream.println : (Z)V>    //  打印boolean值（标识字符Z表示基本数据类型boolean） 此处为true
         *  62 getstatic #6 <java/lang/System.out : Ljava/io/PrintStream;>
         */
        System.out.println(c == d);
        /** false
         *  65 aload 5
         *  67 aload 6
         *  69 if_acmpne 76 (+7)    //比较引用类型，e跟f至为321，超过默认的 -128 - 127 范围，自动装箱生成的是两个对象，因此不相等
         *  72 iconst_1
         *  73 goto 77 (+4)
         *  76 iconst_0             //跳转到此处，对应boolean的false
         *  77 invokevirtual #7 <java/io/PrintStream.println : (Z)V>    //打印false
         *  80 getstatic #6 <java/lang/System.out : Ljava/io/PrintStream;>
         *
         */
        System.out.println(e == f);
        /** true
         *  83 aload_3
         *  84 invokevirtual #8 <java/lang/Integer.intValue : ()I>  //自动拆箱（调用Integer::intValue方法）
         *  87 aload_1
         *  88 invokevirtual #8 <java/lang/Integer.intValue : ()I>
         *  91 aload_2
         *  92 invokevirtual #8 <java/lang/Integer.intValue : ()I>
         *  95 iadd                 //对已拆箱的a跟b相加
         *  96 if_icmpne 103 (+7)   //比较栈顶两int型数值，不相等时跳转，此处c拆箱后是int 3, a跟b拆箱后的int值相加也是3，所以相等，不跳转
         *  99 iconst_1             //会到执行此处，对应boolean的true
         * 100 goto 104 (+4)
         * 103 iconst_0
         * 104 invokevirtual #7 <java/io/PrintStream.println : (Z)V>    //打印true
         * 107 getstatic #6 <java/lang/System.out : Ljava/io/PrintStream;>
         */
        System.out.println(c == (a + b));
        /**
         * true
         * 110 aload_3
         * 111 aload_1
         * 112 invokevirtual #8 <java/lang/Integer.intValue : ()I>
         * 115 aload_2
         * 116 invokevirtual #8 <java/lang/Integer.intValue : ()I>      //算数计算前会拆箱
         * 119 iadd                                                     //相加
         * 120 invokestatic #2 <java/lang/Integer.valueOf : (I)Ljava/lang/Integer;> //对栈顶数值装箱（即119相加结果）
         * 123 invokevirtual #9 <java/lang/Integer.equals : (Ljava/lang/Object;)Z>  //调用包装类型的equals方法   c.equals(120装箱的Integer实例)
         * 126 invokevirtual #7 <java/io/PrintStream.println : (Z)V>
         * 129 getstatic #6 <java/lang/System.out : Ljava/io/PrintStream;>
         */
        System.out.println(c.equals(a + b));
        /**
         * true
         * 132 aload 7
         * 134 invokevirtual #10 <java/lang/Long.longValue : ()J>
         * 137 aload_1
         * 138 invokevirtual #8 <java/lang/Integer.intValue : ()I>
         * 141 aload_2
         * 142 invokevirtual #8 <java/lang/Integer.intValue : ()I>      //算数计算前会拆箱
         * 145 iadd     //相加
         * 146 i2l      //将栈顶int型数值强制转换成long型数值，并将结果压入栈顶
         * 147 lcmp     //比较栈顶两long型数值的大小，并将比较结果（1，0，-1）压入栈顶    此处相等，结果为0
         * 148 ifne 155 (+7)    //当栈顶int型数值不为0时跳转
         * 151 iconst_1     //对应true
         * 152 goto 156 (+4)
         * 155 iconst_0
         * 156 invokevirtual #7 <java/io/PrintStream.println : (Z)V>    //输出true
         * 159 getstatic #6 <java/lang/System.out : Ljava/io/PrintStream;>
         */
        System.out.println(g == (a + b));
        /**
         * false
         * 162 aload 7
         * 164 aload_1
         * 165 invokevirtual #8 <java/lang/Integer.intValue : ()I>
         * 168 aload_2
         * 169 invokevirtual #8 <java/lang/Integer.intValue : ()I>
         * 172 iadd
         * 173 invokestatic #2 <java/lang/Integer.valueOf : (I)Ljava/lang/Integer;>     //装箱 Integer::valueOf
         * 176 invokevirtual #11 <java/lang/Long.equals : (Ljava/lang/Object;)Z>        //Long.equals(Integer实例) 返回false
         * 179 invokevirtual #7 <java/io/PrintStream.println : (Z)V>
         */
        System.out.println(g.equals(a + b));

        //编译不过 Operator '==' cannot be applied to 'java.lang.Long', 'java.lang.Integer'
        //System.out.println(g == c);

    }
}
