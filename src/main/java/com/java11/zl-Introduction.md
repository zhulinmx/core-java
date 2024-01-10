# Java 11官方介绍：
https://www.oracle.com/java/technologies/javase/11-relnote-issues.html

# New Features and Enhancements
- 1、本地变量类型推断 var

局部变量类型推断就是左边的类型直接使用 var 定义，而不用写具体的类型，编译器能根据右边的表达式自动推断类型。
```
      var javastack = "javastack";
      就等于：String javastack = "javastack";
```

- 2、字符串加强 String API enhancement

Java 11 增加了一系列的字符串处理方法，如以下所示。

```
     // 判断字符串是否为空白
             " ".isBlank(); // true
     
     // 去除首尾空格
             " Javastack ".strip(); // "Javastack"
     
     // 去除尾部空格
             " Javastack ".stripTrailing(); // " Javastack"
     
     // 去除首部空格
             " Javastack ".stripLeading(); // "Javastack "
     
     // 复制字符串
             "Java".repeat(3); // "JavaJavaJava"
     
     // 行数统计
```

- 3、集合加强

自 Java 9 开始，Jdk 里面为集合（List/ Set/ Map）都添加了 of 和 copyOf 方法，它们两个都用来创建不可变的集合。


- 4、Stream 加强

Stream 是 Java 8 中的新特性，Java 9 开始对 Stream 增加了以下 4 个新方法。

  - 增加单个参数构造方法，可为null:
  
        Stream.ofNullable(null).count(); // 0
        
  - 增加 takeWhile 和 dropWhile 方法
  
        Stream.of(1, 2, 3, 2, 1)
        .takeWhile(n -> n < 3)
        .collect(Collectors.toList());  // [1, 2]
        // 从开始计算，当 n < 3 时就截止。

        Stream.of(1, 2, 3, 2, 1)
        .dropWhile(n -> n < 3)
        .collect(Collectors.toList());  // [3, 2, 1]
        // 这个和上面的相反，一旦 n < 3 不成立就开始计算。

  - iterate重载

       这个 iterate 方法的新重载方法，可以让你提供一个 Predicate (判断条件)来指定什么时候结束迭代。

- 5、Optional 加强
  
Opthonal 也增加了几个非常酷的方法，现在可以很方便的将一个 Optional 转换成一个 Stream, 或者当一个空 Optional 时给它一个替代的。

```
        Optional.of("javastack").orElseThrow();     // javastack
        Optional.of("javastack").stream().count();  // 1
        Optional.ofNullable(null)
        .or(() -> Optional.of("javastack"))
        .get();   // javastack
```

- 6、InputStream 加强

InputStream 终于有了一个非常有用的方法：transferTo，可以用来将数据直接传输到 OutputStream，这是在处理原始数据流时非常常见的一种用法，如下示例。
 ```
        var classLoader = ClassLoader.getSystemClassLoader();
        var inputStream = classLoader.getResourceAsStream("javastack.txt");
        var javastack = File.createTempFile("javastack2", "txt");
        try (var outputStream = new FileOutputStream(javastack)) {
        inputStream.transferTo(outputStream);
        }
 ```

- 7、HTTP Client API

这是 Java 9 开始引入的一个处理 HTTP 请求的的孵化 HTTP Client API，该 API 支持同步和异步，而在 Java 11 中已经为正式可用状态，你可以在 java.net 包中找到这个 API。
来看一下 HTTP Client 的用法：
 ```
        var request = HttpRequest.newBuilder()
        .uri(URI.create("https://javastack.cn"))
        .GET()
        .build();
        var client = HttpClient.newHttpClient();
         // 同步
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        // 异步
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenAccept(System.out::println);
        上面的 .GET() 可以省略，默认请求方式为 Get！
 ```

8、化繁为简，一个命令编译运行源代码

在我们的认知里面，要运行一个 Java 源代码必须先编译，再运行，两步执行动作。看下面的代码。
 ```
        // 编译
        javac Javastack.java

       // 运行
        java Javastack
 ```
而在未来的 Java 11 版本中，通过一个 java 命令就直接搞定了，如以下所示。

        java Javastack.java

        PlayUnmute
        Loaded: 0.83%

- 9、ZGC垃圾收集器（A Scalable Low-Latency Garbage Collector(Experimental)

   **ZGC, 这应该是JDK11最为瞩目的特性, 没有之一. ZGC是一个并发, 基于region, 压缩型的垃圾收集器, 只有root扫描阶段会STW（stop the world），所以GC停顿时间不会随着堆的增加和存活对象的增加而变长。
   可是后面带了Experimental, 说明这还不建议用到生产环境.**

  - 特性：
       GC暂停时间不会超过10ms
       既能处理几百兆的小堆, 也能处理几个T的大堆(OMG)
       和G1相比, 应用吞吐能力不会降低超过15%
       为将来的GC功能和利用colord指针以及Load barriers优化奠基基础
       初始只支持64位系统（32位的 jdk 只能用到 4 个G，和ZGC的设计目标不符合）

  - ZGC的设计目标是：
       支持TB级内存容量，暂停时间低（<10ms），对整个程序吞吐量的影响小于15%。 未来还能够扩展实现机制，以支持很多使人兴奋的功能，例如多层堆（即热对象置于DRAM和冷对象置于NVMe闪存），或压缩堆。

GC是java主要优点之一. 然而, 当GC停顿太长, 就会开始影响应用的响应时间，消除或者减小GC停顿时长, java将对更普遍的应用场景是一个更有吸引力的平台。
此外, 现代系统中可用内存不断增加,用户和程序员但愿JVM可以以高效的方式充分利用这些内存, 而且无需长时间的GC暂停时间.

ZGC : avg 1.091ms  max:1.681
G1 : avg 156.806  max:543.846

- 10、Epsilon垃圾收集器（A No-Op Garbage Collector）

  - 使用：
        -XX:+UseEpsilonGC

  - 用途：
  开发一个处理内存分配，该收集器不做任何垃圾回收。
  1）用于性能测试
  2）内存压力测试
  3）非常短的job任务
  4）vm接口测试


