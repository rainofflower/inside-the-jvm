package com.yanghui.learn.clazz;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author yanghui
 * @date 2020-05-19 16:43
 **/
public class Main {

//    public static void main(String... args){
//        TestClass testClass = new TestClass();
//        System.out.println(testClass.inc());
//        TestClass2 testClass2 = new TestClass2();
//        System.out.println(testClass2.inc());
//        TestClass3 testClass3 = new TestClass3<Integer>();
//        System.out.println(testClass3.str(5));
//        TestClass4 testClass4 = new TestClass4<Integer>();
//        System.out.println(testClass4.inc(5));
//
//        TestClass5 testClass5 = new TestClass5<Integer>();
//        System.out.println(testClass5.inc(5));
//        TestClass6 testClass6 = new TestClass6();
//        testClass6.test1();
//        testClass6.test2();
//}
//    public static void main(String... args){
//        Integer i1 = 127;//128
//        int i = i1.intValue();
//        Integer i2 = Integer.valueOf(i);
//        System.out.println(i1 == i2);
//        System.out.println(null instanceof Object);
//        System.out.println(i1 instanceof Integer);
////        System.out.println(i instanceof Integer);
//        Double.valueOf(100.0);

//        int a=10 ;
//        long b=10L;
//        double c=10.0;
//        System.out.println(a == b);
//        System.out.println(a == c);
//        ArrayList<Object> arrayList = new ArrayList<>();
//        System.out.println(arrayList);
//        for(int i = 0; i < 100; i++){
//            arrayList.add(i);
//        }
//        System.out.println(arrayList);
//        HashMap<Object, Object> map = new HashMap<>(2,1);
//        for(int i = 0; i < 100; i++){
////            map.put(null, null);
//            map.put(i, null);
//        }
//        System.out.println(map);

        //异或操作交换两个整数
//        int a = 10;
//        int b = 20;
//        a = a ^ b;
//        b = a ^ b;
//        a = a ^ b;
//        System.out.println("a="+a);
//        System.out.println("b="+b);

        //深拷贝、浅拷贝
//        HashMap<Father, Son> reference = new HashMap<>();
//        reference.put(new Father(), new Son());
//        System.out.println(reference);
//        HashMap<Father, Son> cloneRef1 = (HashMap<Father, Son>)reference.clone();
//        System.out.println(cloneRef1 == reference);
//        System.out.println(cloneRef1.equals(reference));
//        String mapJsonStr = JSON.toJSONString(reference);
//        System.out.println(mapJsonStr);
//        HashMap<Father, Son> cloneRef2 = JSON.parseObject(mapJsonStr, new TypeReference<HashMap<Father, Son>>() {
//        });
//        System.out.println(reference == cloneRef2);
//        System.out.println(cloneRef2.equals(reference));
//
//        for(Map.Entry<Father, Son> entry : reference.entrySet()){
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
//        }
//        for(Map.Entry<Father, Son> entry : cloneRef1.entrySet()){
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
//        }
//        for(Map.Entry<Father, Son> entry : cloneRef2.entrySet()){
//            System.out.println(entry.getKey());
//            System.out.println(entry.getValue());
//        }
//    }

    public static void main(String... args){
        byte a = 127;
        byte b = 1;
//        b = a + b; // 报编译错误:cannot convert from int to byte
        b += a;
        System.out.println(b);

        short s1= 1;
//        s1 = s1 + 1;
        s1 += 1;
        System.out.println(s1);
    }
}
