# 异常

**规避异常、捕获异常、分析异常**
**严谨严谨严谨！写程序最重要就是严谨+逻辑**

## 异常的概念
Java异常是Java提供的处理程序错误的一种机制。所谓错误是指在程序运行的时候发生的一些异常事件。
设计好的程序应该在发生异常时提供异常处理的方法，使得程序不会因为异常的发生而阻断或产生不可预期的后果。
（可以理解为我预知了、假设了某种错误的出现，那么针对这种错误我要采取什么手段）
Java程序的执行过程中如果出现异常事件，可以生成一个异常对象，该异常对象封装了异常事件的信息，并被提交给Java运行时系统，这个过程称为抛出异常。
当Java运行时系统接收到异常对象时，会寻找可以处理异常的方法，并把当前异常对象交给其处理，这个过程称为捕获异常。

## Java异常的分类
**Throwable类是java中所有异常的超类，有Exception和Error两个子类。**

Error是程序无法处理的错误，如OutOfMemoryError，stackOverFlowError，NoClassDefFoundError。

Exception是程序本身可以处理的异常，包括运行时异常（RuntimeException）和非运行时异常（Checked Exception）：

（1）RuntimeException是Exception的一个子类，其子类如ArrayIndexOutOfBoundsException、NullPointerException、ClassCastException等。

（2）非运行时异常如IOException、ClassNotFoundException、FileNotFoundException等

# 异常捕获、处理的通用结构

```
   try() {
       //代码
    } catch (XXXException e) {
         //捕获异常后处理
    } catch (YYYException e2) {
         //catch 异常的时候，要先 catch 具体的（小的）异常，然后再 catch 抽象的（大的）异常，层层捕捉
         //这样可以做到精准打击
    } finally {
         //关闭资源
    }

```
       
    