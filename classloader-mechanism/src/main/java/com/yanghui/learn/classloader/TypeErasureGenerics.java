package com.yanghui.learn.classloader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * java泛型相关
 * 类型擦除式泛型
 * erasure / ɪˈreɪʒər / 擦除、消除
 * @param <E>
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
    }

}
