package com.concurrency.art.ch06;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * Java 8中引入了CompletableFuture类，它是一种方便的异步编程工具，可以处理各种异步操作，如网络请求、文件IO和数据库操作等。
 * 它是Java的Future接口的扩展，提供了一些有用的方法来创建、操作和组合异步操作。
 *
 * 在CompletableFuture中的流程编排是通过thenApply、thenAccept、thenCombine等方式来实现的，
 * thenApply：接收上一步的处理结果，进行下一步消费，并返回结果
 * thenAccept：和thenApply类似，不过无结果返回
 * thenCombine：同时接收两个流程节点，等其都执行完毕后一起处理结果
 *
 */
public class T16_CompletableFuture {

    public static void main(String[] args) throws Exception{

        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(22);
            return 20;
        });
        //使用thenCombine()方法组合多个CompletableFuture
        CompletableFuture<Integer> f3 = f1.thenCombine(f2, (result1, result2) -> result1 + result2);

        AtomicInteger seq = new AtomicInteger(0);
        Function<String, String> func = s -> s + " | " + seq.incrementAndGet();

        CompletableFuture<String> a = new CompletableFuture<>();
        CompletableFuture<String> b = a.thenApply(func);
        CompletableFuture<String> c = a.thenApply(func);
        a.complete("ss");
        System.out.println(b.get());
        System.out.println(c.get());
        //当a调用complete函数时（无论是同步还是异步），都会依次触发A任务的stack下挂接的其他依赖任务。
        // 而只要a没有调用complete函数，那么thenApply中挂接的依赖任务无论如何都无法执行（因为a对象的result属性为空）


        //使用CompletableFuture.supplyAsync()方法创建异步执行的Supplier，Supplier中的代码会在异步线程中执行，代码执行完毕后，CompletableFuture将会得到执行结果
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");
        //使用thenCompose()方法组合多个CompletableFuture
        CompletableFuture<String> future3 = future1.thenCompose(
                result1 -> future2.thenApply(result2 -> result1 + " " + result2)
        );

        //有时我们需要等待多个CompletableFuture对象执行完毕后再继续执行下一步操作。
        // 我们可以使用CompletableFuture的allOf()方法或anyOf()方法来等待多个CompletableFuture对象执行完毕。
        CompletableFuture<String> f11 = CompletableFuture.supplyAsync(() -> "you");
        CompletableFuture<String> f22 = CompletableFuture.supplyAsync(() -> "me");
        CompletableFuture<Void> allFuture = CompletableFuture.allOf(f11, f22);
        CompletableFuture<Object> anyFuture = CompletableFuture.anyOf(f11, f22);

        try {
            //当异步操作完成时，可以通过CompletableFuture的get()方法获取执行结果
            System.out.println(future3.get());
            System.out.println(f3.get()); //输出30
            System.out.println(anyFuture.get()); //输出"you"或"me"
            System.out.println(allFuture.get());

            CompletableFuture<String> allFutureResult = allFuture.thenApply(res -> {
                // 通过join函数获取返回值
                String result1 = f11.join();
                String result2 = f22.join();
                return result1 + result2 ;
            });
            System.out.println("步骤all of的结果：" + allFutureResult.get());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("异常信息");
        });

        //当CompletableFuture执行过程中出现异常时，我们需要使用exceptionally()方法来处理异常
        future.exceptionally(ex -> {
            System.out.println(ex.getMessage()); //输出"异常信息"
            return 0;
        });

    }
}
