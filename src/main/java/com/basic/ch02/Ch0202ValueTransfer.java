package com.basic.ch02;

/**
 * 值传递：当一个变量被传递给另一个变量时，实际上是将该变量的值复制到新变量中。这意味着在函数内部对新变量的任何修改都不会影响到原始变量。
 * 引用传递：当一个对象被传递给另一个对象时，实际上是将该对象的引用（内存地址）复制到新对象中。这意味着在函数内部对新对象的任何修改都会影响到原始对象。
 *
 * 除了基本数据类型，都是引用传递
 *
 */
public class Ch0202ValueTransfer {
    public static void main(String[] args) {
        int a = 10;
        int b = a;
        b = 20;
        System.out.println("a: " + a); // 10
        System.out.println("b: " + b); // 20

        Book book1 = new Book("java");
        Book book2 = book1;
        book2.setName("python");

        //book1和book2指向的同一个地址，因此如果book1改变，book2也会看到变化
        System.out.println(book1); //Book{name='python', price=0}
        System.out.println(book2); //Book{name='python', price=0}

    }
}
