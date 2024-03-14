package com.basic.net.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 从java11开始，HttpClient可以用来发送http请求
 *
 * 1) 以get请求上海证券交易所(000001：上证指数；000002：Ａ股指数)为例子：
 * 2) CompletableFuture使用说明见：com.concurrency.art.ch06.T16_CompletableFuture
 */
public class TestHTTP {

    public static void main(String[] args) throws Exception {

        HttpRequest req000001 = HttpRequest.newBuilder()
                .uri(URI.create("http://yunhq.sse.com.cn:32041/v1/sh1/snap/000001?callback=jQuery1124018976618716393323_1709884010610&select=name%2Cprev_close%2Copen%2Chigh%2Clow%2Clast%2Cchange%2Cchg_rate%2Cvolume%2Camount%2Ccpxxlmttype%2Cup_limit%2Cdown_limit%2Ctradephase%2Cbid%2Cask%2Chlt_tag%2Cgdr_ratio%2Cgdr_prevpx%2Cgdr_currency%2Ccpxxprodusta%2Cfp_volume%2Cfp_amount%2Cfp_phase%2Ccpxxextendname&_=1709884010612"))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .GET().build();


        HttpRequest req000002 = HttpRequest.newBuilder()
                .uri(URI.create("http://yunhq.sse.com.cn:32041/v1/sh1/snap/000002?callback=jQuery1124018976618716393323_1709884010610&select=name%2Cprev_close%2Copen%2Chigh%2Clow%2Clast%2Cchange%2Cchg_rate%2Cvolume%2Camount%2Ccpxxlmttype%2Cup_limit%2Cdown_limit%2Ctradephase%2Cbid%2Cask%2Chlt_tag%2Cgdr_ratio%2Cgdr_prevpx%2Cgdr_currency%2Ccpxxprodusta%2Cfp_volume%2Cfp_amount%2Cfp_phase%2Ccpxxextendname&_=1709884010612"))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .GET().build();

        HttpClient client = HttpClient.newHttpClient();

        // 同步请求
        HttpResponse<String> response = client.send(req000001, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());

        System.out.println(111);
        TimeUnit.SECONDS.sleep(2);

        // 异步请求上证指数和A股指数
        CompletableFuture<String> future1 = client.sendAsync(req000001, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);
        CompletableFuture<String> future2 = client.sendAsync(req000002, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body);

        CompletableFuture<String> allFuture = CompletableFuture.allOf(future1, future2).thenApply(res -> {
            String result1 = future1.join();
            String result2 = future2.join();
            return result1 + result2;
        });

        System.out.println("上证指数和A股指数为：" + allFuture.get());

    }
}
