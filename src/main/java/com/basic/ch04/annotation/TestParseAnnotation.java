package com.basic.ch04.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 反射与注解
 */
public class TestParseAnnotation {
    public static void main(String[] args) {
        try {
            // 1. 使用类加载器加载类
            Class c = Class.forName("com.basic.ch04.annotation.Child");

            // 2. 找到类上的注解
            boolean flag = c.isAnnotationPresent(Description.class);
            if (flag) {
            // 3. 拿到注解实例
                Description description = (Description) c.getAnnotation(Description.class);
                System.out.println(description.value());
            }

            // 4. 找到方法上的注解
            Method[] ms = c.getMethods();
            for (Method m : ms) {
                boolean annotationPresent = m.isAnnotationPresent(Description.class);
                if (annotationPresent) {
                    Description description = m.getAnnotation(Description.class);
                    System.out.println("used in method:"+ m.getName());
                    System.out.println(description.value());
                }
            }

            // 5. 另一种解析方法
            for (Method m : ms) {
                Annotation[] as = m.getAnnotations();
                for (Annotation a : as) {
                    if (a instanceof Description) {
                        Description description = (Description) a;
                        System.out.println(description.value());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}