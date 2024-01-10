package com.basic.ch02;

/**
 * final关键字：可以用于修饰类、方法、变量
 *    被final修饰的类不可以被继承；
 *    被final修饰的方法不可以被重写；
 *    被final修饰的变量不可以被改变.如果修饰引用,那么表示引用不可变,引用指向的内容可变；
 *    被final修饰的方法,JVM会尝试将其内联,以提高运行效率；
 *    被final修饰的常量,在编译阶段会存入常量池中；
 *
 */
public class Ch0205Final {
	public static void main(String[] args) {
		T t = new T();
		//t.i = 8;
	}
}

final class T {
	final int i = 8;
	public final void m() {
		/**
		 *  i 是final修饰的变量，不可以改值
		 */
		// i = 9;
	}
}

/**
 * T 是被 final 修饰的类， 不可以被继承
 */
//class TT extends T {
//
//}