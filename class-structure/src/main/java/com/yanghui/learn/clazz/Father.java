package com.yanghui.learn.clazz;

import java.io.IOException;

public class Father {

    public static void main(String[] args) throws ClassNotFoundException{
        Son son = new Son();
        son.say(null);
    }
    protected Father say(Father f) throws ClassNotFoundException{
        System.out.println("say f");
        return null;
    }

}

class Son extends Father{

    @Override
    public Son say(Father f) throws ClassNotFoundException {
        System.out.println("say s");
        return null;
    }
}
