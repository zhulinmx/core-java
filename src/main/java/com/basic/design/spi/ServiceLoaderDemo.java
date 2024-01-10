package com.basic.design.spi;


import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * SPI机制：动态绑定
 *
 * 1. META-INF/services/目录创建一个文件名LoadBalance全限定名的文件
 * 2. ServiceLoader获取
 *
 */
public class ServiceLoaderDemo {

    public static void main(String[] args) {
        ServiceLoader<LoadBalance> loadBalanceServiceLoader = ServiceLoader.load(LoadBalance.class);
        Iterator<LoadBalance> iterator = loadBalanceServiceLoader.iterator();
        while (iterator.hasNext()) {
            LoadBalance loadBalance = iterator.next();
            loadBalance.init();
       }
    }
}
