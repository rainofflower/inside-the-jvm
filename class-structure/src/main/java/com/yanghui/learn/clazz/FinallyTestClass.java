package com.yanghui.learn.clazz;

/**
 *
 * @author yanghui
 * @date 2020-05-20 16:42
 **/
public class FinallyTestClass {

    public static void main(String... args){
        FinallyTestClass testClass = new FinallyTestClass();
        System.out.println(testClass.test02());
        System.out.println(testClass.inc());
    }

    public int test02() {
        int b = 20;
        try {
//            System.out.println("try block");
            int c = b / 0;
            return b+=80;
        } catch (Exception e) {
//            System.out.println("catch block");
            return --b;
        } finally {
//            System.out.println("finally block");
            ++b;
        }
    }

    public int inc(){
        int x;
        try{
            x = 1;
            return x;
        }catch (Exception e){
            x = 2;
            return x ;
        }finally{
            x = 3;
        }
    }
}
