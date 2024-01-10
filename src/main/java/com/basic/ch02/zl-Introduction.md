# JAVA——面向对象语言

**意味着：万事万物皆对象，你中有我，我中有你**

# 面向过程和面向对象

- 面向过程设计思想
  一切以我为中心
  我第一步要干什么；
  第二步要干什么；
         
- 面向对象设计思想
  一个工厂的不同工人在工作
  工厂（是个对象），有很多工人（也是对象）
  工人的姓名是属性，工作是方法

# JAVA对象和类
  
- 对象和类（class）的概念
  类是抽象的：具有某些特征的东西。一类事物的抽象。譬如说是个工人
  对象是具体的：某个类的具体。譬如说这个叫小花的工人
  陀思妥耶夫斯基说要爱具体的人，不要爱抽象的人。这句话的意思就是你爱的要是个能呼吸的、会说情话、有身份证的人，而不是一个空洞的没有名字的人（解释权归我所有）。
  
- 类之间的关系（可以找两张UML图看看）
  依赖 < 关联（我这个方法的参数是你这个类的对象） < 聚合 < 组合
  可以找两张UML图看看
  继承（extends）：父与子
  聚合：整体与局部的关系，...是...的一部分。
  组合：密不可分，...是...必不可少的一部分。
  实现关系：implements

- 对象和引用
  引用：Java语言中除基本类型之外的变量类型都称为引用类型。
  Java堆内存中的对象是通过栈内存中的引用对其操作的
  占两块儿内存，堆内存，栈内存

## Java类里有什么？或者说会出现的东西
- 构造函数
         public 类名() {}
         new 一个东西的时候要调用的方法，叫做构造方法。
         一旦你自己重写构造方法，那么Java 编译器将不会为这个类添加默认的构造方法         
         对象的创建和使用：
                  Object o = new Object();
                  o.equals(new Object);
                  
         
- this 关键字
         当前对象
         this(String...) 对应的构造方法
         this.methodName 本类方法
         
         
- super 关键字
         调用父类的内容
         super(String...) 对应父类构造函数
         super.methodName 父类方法
         
- static 关键字
         修饰类变量
         修饰类方法
         
- package 关键字
         解决类名冲突的问题
         命名：公司域名倒过来，默认为default包
         包对应文件系统中的文件夹
         根据业务分包
         根据功能分包
         如果向让别的人用你的类，首先要先用 import 关键字将其引入
         
- import 关键字
         引入别的类库  
                
- final 关键字
         类似于C语言中的 const，可以用于修饰类、方法、变量
         被final修饰的类不可以被继承
         被final修饰的方法不可以被重写
         被final修饰的变量不可以被改变.如果修饰引用,那么表示引用不可变,引用指向的内容可变.
         被final修饰的方法,JVM会尝试将其内联,以提高运行效率
         被final修饰的常量,在编译阶段会存入常量池中.
         除此之外,编译器对final域要遵守的两个重排序规则更好:
         在构造函数内对一个final域的写入,与随后把这个被构造对象的引用赋值给一个引用变量,这两个操作之间不能重排序
         初次读一个包含final域的对象的引用,与随后初次读这个final域,这两个操作之间不能重排序.


## 类的访问控制

- Java类访问控制 public protected private default（默认）
  
         修饰符       类内部       同一个包     子类      任何地方（不同包）
         private       Y           N          N          N
         default       Y           Y          N          N
         protected     Y           Y          Y          N
         public        Y           Y          Y          Y

  对于类来说public意味着任何地方都可以使用这个类，访问权限最宽；private权限最小


# OOP三大特征: 封装、继承、多态  
       
         
# 类的继承 extends
- 双亲委派机制
      

## 方法的重写 @Override
- 子类继承父类，具有相同的函数名称、相同的参数列表、相同的方法返回值
- 重写的方法不可以使用比被重写方法更严格的访问权限             
     
   
## 对象转型 upcasting downcasting
- 父类引用指向子类对象
- 一个基类的引用不可以访问其子类对象新加的成员（属性和方法）
- 可以使用 引用变量 instanceof 类名，来判断该引用类型变量所指向的对象是否属于该类或该类的子类
- 子类的对象可以当作基类的对象来使用称向上转型(upcasting),反之，称为下转型
    

## 多态 Polymorphism
- 存在继承关系
- 方法重写
- 父类引用指向子类对象
- 实际中new的谁，就调用谁的方法，函数指针，动态绑定，核心中的核心
- 关于动态绑定和静态绑定：https://blog.csdn.net/weixin_33816300/article/details/94086184?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-94086184-blog-5569617.pc_relevant_3mothn_strategy_recovery&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-94086184-blog-5569617.pc_relevant_3mothn_strategy_recovery&utm_relevant_index=2


# 抽象类 Abstract
- 用 abstract 修饰的类为抽象类， 修饰的方法为 抽象方法
- 抽象方法 public abstract void method()
  1. 没有必要实现（相当于 C ++ 中的纯虚函数）;
  2. 当一个类中有抽象方法的时候，那么这个类必须被声明为抽象类;

- 抽象类 public abstract class ...
  1. 必须被继承，抽象方法必须被重写
  2. 抽象类本身不可以 new 出对象
  3. 抽象类中的方法只需声明，不需要实现
            
# 接口 interface implements
- 多个无关的类可以实现同一个接口
- 一个类可以实现多个无关的接口（单继承、多实现）
- 与继承关系类似，接口与实现类之间存在多态性
- 接口是抽象方法和常量值的定义的集合，接口是一种特殊的抽象类，这种抽象类中只包含常量和方法的定义，而没有变量和方法的实现。

- 接口的特性：
1. 接口可以多重实现
2. 接口中声明的属性默认为 public static final 的，也只能是 public static final的
3. 接口中只能定义抽象方法，这些接口默认为 public 的，也只能是 public 的
4. 接口可以继承其他接口，并添加新的属性和成员方法


# object 类
- 有哪些主要方法：clone(), equals(), hashCode(), toString(), notify(), notifyAll(), wait(), finalize(), getClass()
     

# Java匿名类是一种没有名称的类，它通常用于创建一次性的对象或实现一个接口或抽象类。
匿名类的语法如下：
new InterfaceName() {
// 实现接口中的方法
}

