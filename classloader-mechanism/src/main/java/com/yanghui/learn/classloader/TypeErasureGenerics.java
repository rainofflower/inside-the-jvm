package com.yanghui.learn.classloader;

import java.lang.reflect.Array;
import java.util.*;

/**
 * java泛型相关、迭代器、装箱、拆箱、循环体控制流字节码指令、回边指令
 * 类型擦除式泛型
 * erasure / ɪˈreɪʒər / 擦除、消除
 */
public class TypeErasureGenerics<E> {

    //java中不支持的泛型用法
/*    public void doSomeThing(Object item){
        if(item instanceof E){  //不合法，无法对泛型进行实例判断

        }
        E newItem = new E();    //不合法，无法使用泛型创建对象
        E[] itemArray = new E[10];  //不合法，无法使用泛型创建数组
    }*/

    public void covariant(){
        //java中的数组支持协变（covariant）
        Object[] strings = new String[10];
        //strings[0] = 10;    //编译期不会报错，运行时报错 java.lang.ArrayStoreException: java.lang.Integer

        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(10)); //编译器，运行期都不会报错
        arrayList.add("hello world");
        //正常打印 [10, hello world]
        System.out.println(arrayList);
    }

    public void test1(){
        ArrayList<Integer> integerList = new ArrayList<>();
        ArrayList<Object> objectList = new ArrayList<>();
        ArrayList list;     //裸类型
        list = integerList; //子类到父类的安全转型
        list = objectList;
        //ArrayList<int> intList; //编辑报错，由于原始类型不支持到Object的转型，擦除法实现泛型不支持原始类型，提示：Type argument cannot be of primitive type
    }

    public void test2(){
        Map<String, String> map = new HashMap<>();
        map.put("hello", "你好");
        map.put("how are you?", "你吃了没？");
        System.out.println(map.get("hello"));
        System.out.println(map.get("how are you?"));
    }

    //由于运行期java虚拟机无法获取到泛型类型信息，不得不加入的类型参数（从一个额外参数传入一个类型：下方的clazz）
    public static <T> T[] convert(List<T> list, Class<T> clazz) {
        T[] array = (T[]) Array.newInstance(clazz, list.size());
        for(int i = 0; i < list.size(); i++){
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * 跟下一个方法一起存在无法编译通过
     * 提示：'method(List<String>)' clashes with 'method(List<Integer>)'; both methods have same erasure
     */
    /*public static void method(List<String> list){

    }

    public static void method(List<Integer> list){

    }*/

    /**
     * zulu-8.jdk编译不通过
     */
    /*public static String method(List<String> list){
        System.out.println("invoke method(List<String> list)");
        return "";
    }

    public static int method(List<Integer> list){
        System.out.println("invoke method(List<Integer> list)");
        return 1;
    }*/

    public static void main(String[] args) {
        TypeErasureGenerics typeErasureGenerics = new TypeErasureGenerics();
        typeErasureGenerics.covariant();
        typeErasureGenerics.test1();
        typeErasureGenerics.test2();
        ArrayList<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(3);
        Integer[] array = convert(integerList, Integer.class);
        System.out.println(array.length);

        //泛型、变长参数、自动装箱
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        int sum = 0;
        /**
         * 遍历循环最终被还原为迭代器实现（被遍历的类必须实现Iterable接口的原因）
         * int i;
         * for(Iterator var6 = list.iterator(); var6.hasNext(); sum += i) {
         *     i = (Integer)var6.next();
         * }
         * 上面var6.next()实际还存在自动拆箱
         * 见下方字节码133
         * 113 aload 6
         * 115 invokeinterface #34 <java/util/Iterator.hasNext : ()Z> count 1
         * 120 ifeq 148 (+28)       //当栈顶int型值等于0时跳转 （跳转到字节码序号148）
         * 123 aload 6
         * 125 invokeinterface #35 <java/util/Iterator.next : ()Ljava/lang/Object;> count 1
         * 130 checkcast #28 <java/lang/Integer>
         * 133 invokevirtual #36 <java/lang/Integer.intValue : ()I>
         * 136 istore 7
         * 138 iload 5
         * 140 iload 7
         * 142 iadd
         * 143 istore 5
         * 145 goto 113 (-32)       //向后跳转，回边指令
         * 148 getstatic #8 <java/lang/System.out : Ljava/io/PrintStream;>
         */
        for(int i : list){
            sum += i;
        }
        System.out.println(sum);
    }

}
