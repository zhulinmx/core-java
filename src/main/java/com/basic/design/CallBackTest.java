package com.basic.design;

/**
 * 回调机制，函数当作参数传递
 */
public class CallBackTest {
    public void method2(Iexample callback) {
        System.out.println("-----------call method2------");
        callback.method1();
    }

    public static void main(String[] args) {
        Roo roo = new Roo();
        CallBackTest too = new CallBackTest();

        too.method2(roo::method1);
        too.method2(roo);
    }
}

class Roo implements Iexample {

    @Override
    public void method1(){
        System.out.println("-----------call method1-------");
    }

}

interface Iexample {
    void method1();

}
