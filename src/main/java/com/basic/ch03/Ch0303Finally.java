package com.basic.ch03;


/**
 *
 * 对于“try-catch-finally”，若 try 语句块中包含“return”语句，finally 语句块会执行吗？
 * ---会执行，但是最好不要在 finally 块中使用 return，因为finally不是干这些事的，finally块的用处对资源对象、流对象进行关闭。
 * 只有两种情况 finally 块中的语句不会被执行：1.调用了 System.exit()方法； 2. JVM“崩溃”了。
 *
 */
public class Ch0303Finally {

    public static void main(String[] args) {
        System.out.println(test());
    }

    public static int test() {
        try {
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return 2;
        }
    }
}
