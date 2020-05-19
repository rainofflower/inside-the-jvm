package com.yanghui.learn.jvm.performance.tuning;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * jvisualvm 右键进程，选择Trace Application
 *
 * 输入最下方代码
 *
 * @author YangHui
 */
public class BTraceTest {

    public int add(int a, int b){
        return a + b;
    }

    public static void main(String... args) throws Exception {
        BTraceTest test = new BTraceTest();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i<10; i++){
            br.readLine();
            int a = (int) Math.round(Math.random() * 1000);
            int b = (int) Math.round(Math.random() * 1000);
            System.out.println(test.add(a, b));
        }
    }

}


/*
@BTrace
public class TracingScript {

    @OnMethod(clazz="com.yanghui.learn.jvm.performance.tuning.BTraceTest",method="add",location=@Location(Kind.RETURN))

    public static void func(@Self com.yanghui.learn.jvm.performance.tuning.BTraceTest test,int a, int b,@Return int result){
        println("调用堆栈：");
        jstack();
        println(strcat("方法参数A:",str(a)));
        println(strcat("方法参数B:",str(b)));
        println(strcat("方法结果:",str(result)));
    }
}*/
