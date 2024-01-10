package com.basic.ch02;

/**
 *
 * "=="判断的是两个对象的内存地址是否一样，适用于原始数据类型和枚举类型 （它们的变量存储的是值本身，而引用类型变量存储的是引用）；
 * equals 是 Object 类的方法，Object 对它的实现是比较内存地址，我们可以重写这个方法来自定义 “相等”这个概念。
 * 比如类库中的 String、Date 等类就对这个方法进行了重写。
 * 综上，对于枚举类型和原始数据类型的相等性比较，应该使用"=="；对于引用类型的相等性比较，应该使用 equals 方法。
 *
 *
 * hashCode方法可以这样理解：
 * 它返回的就是根据对象的内存地址换算出的一个值。这样一来，当集合
 * 要添加新的元素时，先调用这个元素的hashCode方法，就一下子能定位到它应该放置的物理位置上。
 * 如果这个位置上没有元素，它就可以直接存储在这个位置上，不用再进行任何比较了；如果这个位置上
 * 已经有元素了，就调用它的equals方法与新元素进行比较，相同的话就不存了，不相同就散列其它的地
 * 址。这样一来实际调用equals方法的次数就大大降低了，几乎只需要一两次。
 *
 */
public class Ch0209Equals {

    public static void main(String[] args) {
        Circle c1 = new Circle(new Point(1.0, 2.0), 2.0);
        Circle c2 = new Circle(5.0);
        Circle c3 = c2;
        System.out.println("c1 area = " + c1.area());
        System.out.println("c1 area = " + c2.area());

        Point p1 = new Point(5.2, 6.3);
        System.out.println(c1.contains(p1));

    }
}

class Circle {
    private Point p;
    private double radius;

    Circle(Point p, double r) {
        p = p;
        radius = r;
    }

    Circle(double r) {
        p = new Point(0.0, 0.0);
        radius = r;
    }

    public Point getP() {
        return p;
    }

    public void setP(Point p) {
        this.p = p;
    }

    boolean contains(Point p) {
        double x = p.getX() - this.p.getX();
        double y = p.getY() - this.p.getY();
        if (x * x + y * y > radius * radius) return false;
        else return true;
    }


    public double getRadius() {
        return radius;
    }

    public void setRadius(double r) {
        radius = r;
    }

    public double area() {
        return 3.14 * radius * radius;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        else if (obj instanceof Point) {
            Circle circle = (Circle) obj;
            if(circle.getP().getX() ==this.getP().getX() && circle.getP().getY() ==this.getP().getY() && circle.getRadius()==this.getRadius())
                return true;
        }
        return false;
    }
}
