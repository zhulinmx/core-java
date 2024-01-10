package com.basic.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型是Java SE 1.5之后的特性，《Java 核心技术》中对泛型的定义是：“泛型” 意味着编写的代码可以被不同类型的对象所重用。
 *
 * 你可以写一个泛型方法，该方法在调用时可以接收不同类型的参数。根据传递给泛型方法的参数类型，编译器适当地处理每一个方法调用。
 * 下面是定义泛型方法的规则：
 * 所有泛型方法声明都有一个类型参数声明部分（由尖括号分隔），该类型参数声明部分在方法返回类型之前（在下面例子中的 <E>）。
 * 每一个类型参数声明部分包含一个或多个类型参数，参数间用逗号隔开。一个泛型参数，也被称为一个类型变量，是用于指定一个泛型类型名称的标识符。
 * 类型参数能被用来声明返回值类型，并且能作为泛型方法得到的实际参数类型的占位符。
 * 泛型方法体的声明和其他方法一样。
 * 注意类型参数只能代表引用型类型，不能是原始类型（像 int、double、char 等）。
 *
 * java 中泛型标记符：
 * E - Element (在集合中使用，因为集合中存放的是元素)
 * T - Type（Java 类）
 * K - Key（键）
 * V - Value（值）
 * N - Number（数值类型）
 * ？ - 表示不确定的 java 类型
 *
 * !!! 不能创建泛型数组因为会：运行擦除
 * PECS原则是泛型编程的一个重要概念。全称是"Producer Extends Consumer Super"，主要涉及到生产者和消费者之间的数据传递。
 *  - get方法：<? extend T>
 *  - set方法：<? super T>
 *
 *  可以多看看集合的各种类，学学其中的泛型用法，起步学习多看看别人怎么写
 *
 */
public class GenericTypes {

    public static void method (List<Integer> list, int a) {
        System.out.println("void method (List<String> list)");
    }

    public static int method (List<Integer> list, int a, int b) {
        System.out.println("void method (List<String> list)");
        return 2;
    }

    public static int method (List<Integer> list) {
        System.out.println("void method (List<String> list)");
        return 1;
    }

    /**
     * 无法编译
     * 泛型擦除
     */

//    public static int method (List<String> list) {
//        System.out.println("void method (List<String> list)");
//        return 3;
//    }

    /**
     * 类型通配符一般是使用 ? 代替具体的类型参数。例如 List<?> 在逻辑上是 List<String>,List<Integer> 等所有 List<具体类型实参> 的父类。
     * @param data
     */
    public static void getData(List<?> data) {
        System.out.println("data :" + data.get(0));
    }

    /**
     * 类型通配符上限通过形如List来定义，如此定义就是通配符泛型值接受Number及其下层子类类型。
     * @param data
     */
    public static void getUpNumber(List<? extends Number> data) {
        System.out.println("data :" + data.get(0));
    }



    public static void main(String[] args) {
        method(new ArrayList<Integer>());

        List<String> name = new ArrayList<String>();
        List<Integer> age = new ArrayList<Integer>();
        List<Number> number = new ArrayList<Number>();

        name.add("icon");
        age.add(18);
        number.add(314);

        getData(name);
        getData(number);
        getUpNumber(age);

    }
}
