package com.yanghui.learn.classloader;

/**
 * javap查看内部类命令
 * javap -v FieldHasNoPolymorphic\$Son
 * 在UNIX中，需要对$转义，其他系统中不需要加\
 */
public class FieldHasNoPolymorphic {

    static class Father{
         public int money = 1;
         public Father(){
             money = 2;
             /**
              * 如果showMoney是虚方法（比如public修饰），该构造方法字节码如下
              * 14 aload_0
              * 15 invokevirtual #3 <com/yanghui/learn/classloader/FieldHasNoPolymorphic$Father.showMoney : ()V>
              * 注意15，使用的是invokevirtual指令，该指令使用动态分派决定调用哪个版本的方法，即根据14字节码加载的对象决定调用哪个方法
              *
              * 如果showMoney是非虚方法（比如private修饰），该构造方法字节码如下
              * 14 aload_0
              * 15 invokespecial #3 <com/yanghui/learn/classloader/FieldHasNoPolymorphic$Father.showMoney : ()V>
              * 注意15，使用的是invokespecial指令，该指令使用静态分派决定调用哪个方法
              */
             showMoney();
         }


        public void showMoney(){
            System.out.println("i am father, i have " + money);
        }

//        private void showMoney(){
//             System.out.println("i am father, i have " + money);
//         }
    }

    static class Son extends Father{
        public int money = 3;

        public Son(){
            money = 4;
            showMoney();
        }

        public void showMoney(){
            System.out.println("i am son, i have " + money);
        }

//        private void showMoney(){
//            System.out.println("i am son, i have " + money);
//        }
    }

    public static void main(String[] args) {
        /**
         * 该命令输出结果
         *
         * 当showMoney是虚方法时输出
         * i am son, i have 0
         * i am son, i have 4
         * this guy has 2
         * this guy has 4
         * 虚方法,aload_0加载到栈顶的对象的实际类型是Son,先去Son中找与常量池中描述符和简单名称都相符的方法
         * 描述符()V
         * 简单名称showMoney
         * 找到了进行访问权限校验，通过了就返回方法的直接引用
         *
         * 当showMoney是非虚方法时输出
         * i am father, i have 2
         * i am son, i have 4
         * this guy has 2
         * this guy has 4
         * 原因可以看Father构造方法里关于字节码的解析
         *
         *
         */
        Father guy = new Son();
        /**
         * guy.money 字节码指令如下
         * 23: aload_1
         * 24: getfield      #9                  // Field com/yanghui/learn/classloader/FieldHasNoPolymorphic$Father.money:I
         */
        System.out.println("this guy has "+guy.money);
        /**
         * ((Son)guy).money 字节码指令如下
         *  51: aload_1
         *  52: checkcast     #2                  // class com/yanghui/learn/classloader/FieldHasNoPolymorphic$Son
         *  55: getfield      #13                 // Field com/yanghui/learn/classloader/FieldHasNoPolymorphic$Son.money:I
         */
        System.out.println("this guy has "+((Son)guy).money);
    }
}
