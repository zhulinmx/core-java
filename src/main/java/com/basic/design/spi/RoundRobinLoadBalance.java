package com.basic.design.spi;

public class RoundRobinLoadBalance implements LoadBalance{
    @Override
    public void init() {
        System.out.println("this is RoundRobinLoadBalance");
    }
}