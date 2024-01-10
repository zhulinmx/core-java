package com.basic.ch01;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal应用
 *
 */
public class Ch0108BigDecimal {
    public static void main(String[] args) {

        float fa = 1.0f - 0.9f;
        float fb = 0.9f - 0.8f;
        if (fa == fb) {
            System.out.println("true");
        } else {
            System.out.println("false"); //fa和fb不相等
        }

        //第一，在使用BigDecimal构造函数时，尽量传递字符串而非浮点类型；第二，如果无法满足第一条，则可采用BigDecimal#valueOf方法来构造初始化值。
        BigDecimal a = new BigDecimal(0.1); //会估算精度
        System.out.println(a);

        //禁止使用构造方法 BigDecimal(double) 的方式把 double 值转化为 BigDecimal 对象。
        //说明：BigDecimal(double) 存在精度损失风险，在精确计算或值比较的场景中可能会导致业务逻辑异常。如：
        //BigDecimal g = new BigDecimal(0.1F)；实际的存储值为：0.100000001490116119384765625
        //优先推荐入参为 String 的构造方法，或使用 BigDecimal 的 valueOf 方法，此方法内部其实执行了 Double 的toString，
        //而 Double 的 toString 按 double 的实际能表达的精度对尾数进行了截断。
        BigDecimal b = new BigDecimal("0.1"); // 推荐使用
        BigDecimal t = BigDecimal.valueOf(0.1);
        System.out.println(b);
        System.out.println(t);

        //通常情况，如果比较两个BigDecimal值的大小，采用其实现的compareTo方法；如果严格限制精度的比较，那么则可考虑使用equals方法。
        BigDecimal a1 = new BigDecimal("0.01");
        BigDecimal b1 = new BigDecimal("0.010");
        System.out.println(a.equals(b1)); //false
        System.out.println(a1.equals(b1)); //false
        System.out.println(a1.compareTo(b1) == 0); //true

        //设置浮点精度
        //如果在除法（divide）运算过程中，如果商是一个无限小数（0.333…），而操作的结果预期是一个精确的数字，那么将会抛出ArithmeticException异常
        BigDecimal aa = new BigDecimal("1.0");
        BigDecimal bb = new BigDecimal("3.0");
        BigDecimal c = aa.divide(bb, 2, RoundingMode.HALF_UP);
        System.out.println(c);
    }
}
