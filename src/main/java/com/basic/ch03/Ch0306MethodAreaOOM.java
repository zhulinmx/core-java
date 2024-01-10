package com.basic.ch03;

/**
 * 永久代是 HotSot 虚拟机对方法区的具体实现，存放了已被虚拟机加载的类信息、常量、静态变量、JIT编译后的代码等。
 * 在Java8后，永久代有了一个新名字：元空间，元空间使用的是本地内存。永久代里存在的信息也有了若干变化：
 * 字符串常量由永久代转移到堆中；和永久代相关的JVM参数已移除。 
 *
 * 出现永久代或元空间的溢出的原因可能有如下几种：
 * 1. 有频繁的常量池操作（eg. String.intern），这种情况只适用于Java7之前应用； 
 * 2. 加载了大量的类信息，且没有及时卸载； 
 *
 * 分析一定要注意究竟使用的是什么java版本
 *
 * VM Args：-XX:PermSize=10M -XX:MaxPermSize=10M
 *
 */
public class Ch0306MethodAreaOOM {

    public static void main(String[] args) {
        // 借助CGLib使得方法区出现内存溢出异常

//        while (true) {
//            Enhancer enhancer = new Enhancer();
//            enhancer.setSuperclass(OOMObject.class);
//            enhancer.setUseCache(false);
//            enhancer.setCallback(new MethodInterceptor() {
//                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//                    return proxy.invokeSuper(obj, args);
//                }
//            });
//            enhancer.create();
//        }

    }

    static class OOMObject {
    }
}