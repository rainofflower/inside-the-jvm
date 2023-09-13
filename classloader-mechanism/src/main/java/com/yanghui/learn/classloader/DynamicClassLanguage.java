package com.yanghui.learn.classloader;

/**
 * 动态类型语言的概念
 */
public class DynamicClassLanguage {

    public static void main(String[] args) {
        //编译可以通过，运行报java.lang.NegativeArraySizeException
        int[][][] array = new int[1][0][-1];

    }
}
