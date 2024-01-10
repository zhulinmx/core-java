package com.basic.ch02;

import java.lang.reflect.*;

/**
 * 反射
 *
 * 获取Class对象，有4中方法：
 * 1）Class.forName(“类的路径”)；
 * 2）类名.class
 * 3）对象名.getClass()
 * 4）基本类型的包装类，可以调用包装类的Type属性来获得该包装类的Class对象
 *
 *
 * 反射机制的优缺点：
 * 优点：
 * 1）能够运行时动态获取类的实例，提高灵活性；
 * 2）与动态编译结合；
 * 缺点：
 * 1）使用反射性能较低，需要解析字节码，将内存中的对象进行解析。
 *   解决方案：
 *   1、通过setAccessible(true)关闭JDK的安全检查来提升反射速度；
 *   2、多次创建一个类的实例时，有缓存会快很多
 *   3、ReflectASM工具类，通过字节码生成的方式加快反射速度
 * 2）相对不安全，破坏了封装性（因为通过反射可以获得私有方法和属性）
 *
 *
 */
public class Ch0224Reflect {
	public static void main(String[] args) throws Exception {
		//m1();
		//m2();
		//m3("java.lang.Thread");
		//m4();
		//m5();
		//m6();
		//String s = "java.lang.String"; //从文件里读出来的
		//new s()
		//m7();
		//m8();
		//m9();
		m10();
	}

	private static void m1() {
		String s = new String();
		Class c = s.getClass();
		System.out.println(c);
		Class su = c.getSuperclass();
		System.out.println(su);
		System.out.println(su.getSuperclass());
	}

	private static void m2() {
		Class c = String.class;
		System.out.println(c);
	}

	private static void m3(String className) {
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			System.out.println("this class doesn't exist!");
		}
	}

	private static void m4() {
		int m = String.class.getModifiers();
		System.out.println(Modifier.isPublic(m));
		System.out.println(Modifier.isFinal(m));
		System.out.println(Modifier.isStatic(m));
	}

	private static void m5() {
		for(Class c : String.class.getInterfaces()) {
			System.out.println(c);
		}
		System.out.println(Comparable.class.isInterface());
	}

	private static void m6() {
		Field[] fs = System.class.getFields();
		for(Field f : fs) {
			System.out.println(f);
		}
	}

	private static void m7() throws Exception {
		Constructor[] cs = String.class.getConstructors();
		for(Constructor c : cs) {
			System.out.println(c);
			for(Class paraClass : c.getParameterTypes()) {
				System.out.print(paraClass + " ");
			}
			System.out.println();
		}

		String.class.newInstance();
	}

	private static void m8() throws Exception {
		Class[] argClasses = new Class[] {int.class, int.class};
		Object[] args = new Object[] {new Integer(12), new Integer(24)};
		Constructor c = java.awt.Point.class.getConstructor(argClasses);
		Object o = c.newInstance(args);
		System.out.println(o);
	}

	private static void m9() throws Exception {
		Class[] argClasses = new Class[] {String.class};
		Object[] args = new Object[] {new String("world!")};
		Method m = String.class.getMethod("concat", argClasses);
		String result = (String)m.invoke(new String("hello"), args);
		System.out.println(result);
	}

	private static void m10() throws Exception {
		/*
		Class cls = Class.forName("java.lang.String");
		Object arr = Array.newInstance(cls, 10);
		Array.set(arr, 5, "this is a test");
		String s = (String)Array.get(arr, 5);
		System.out.println(s);
		*/

		//例中创建了一个 5 x 10 x 15 的整型数组，并为处于 [3][5][10] 的元素赋了值为 37。
		//注意，多维数组实际上就是数组的数组，例如，第一个 Array.get 之后，
		//arrobj 是一个 10 x 15 的数组。进而取得其中的一个元素，即长度为 15 的数组，
		//并使用 Array.setInt 为它的第 10 个元素赋值。

		int dims[] = new int[]{5, 10, 15};
		Object arr = Array.newInstance(Integer.TYPE, dims);
		Object arrobj = Array.get(arr, 3);
		Class cls = arrobj.getClass().getComponentType();
		System.out.println(cls);
		arrobj = Array.get(arrobj, 5);
		Array.setInt(arrobj, 10, 37);
		int arrcast[][][] = (int[][][]) arr;
		System.out.println(arrcast[3][5][10]);

		/**
		 *
		 * 那我们为什么需要这样一个Class对象呢?
		 * 是因为这样，当我们再new一个新对象或者引用静态成员变量时，JVM中的类加载器子系统会把对应Class对象加载到JVM中，再然后根据JVM这个类型信息相关的Class对象创建我们需要实例对象或者提供静态变量的引用值。
		 * 特别需要注意的是，手动编写的每个class类，无论创建多少个实例对象，在JVM中都只有一个Class对象，
		 * 即在内存中每个类有且只有一个相对应的Class对象，可以通过以下方式获得Class对象的方法
		 *
		 */
		System.out.println("reflect testing...");
		Class clz2 = Class.forName("com.basic.ch02.Book");
		System.out.println("loaded book class to JVM");
		Book b1 = new Book("b1");
		Book b2 = new Book("b2");
		/**
		 *
		 * Class.forName()是在类的实例存在即XX.class文件存在，调用该方法让JVM将该文件加载进JVM，成为JVM里的一份字节码文件，也就是Class对象。
		 * 不同于ClassLoader的是，Class.forName默认会初始化类，也就是load进JVM后，还会执行类里的静态方法和静态成员变量的赋值。
		 *
		 */
		Class clz = b1.getClass();
		Class clz1 = b2.getClass();
		Class clz3 = Book.class;

		System.out.println(clz);
		System.out.println(clz1);
		System.out.println(clz2);
		System.out.println(clz2 == clz3);

		Method m1 = clz.getDeclaredMethod("print", String.class);
		m1.invoke(b1, new Object[] {"you"});
		m1.invoke(b2, "he");

	}
}