package com.basic.net.http;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;

/**
 * 从11开始，HttpClient可以用来发送http请求
 */
public class TestHTTP {

    public static void main(String[] args) throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://foo.com/"))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("src/main/java/com/basic/net/http/test.json")))
                .build();

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(20))
                .proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 80)))
                .authenticator(Authenticator.getDefault())
                .build();

        //同步请求
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());

        //异步请求
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                 .thenApply(HttpResponse::body)
                 .thenAccept(System.out::println);

    }
}
