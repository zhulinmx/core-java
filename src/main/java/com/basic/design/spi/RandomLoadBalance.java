package com.basic.design.spi;

public class RandomLoadBalance implements LoadBalance{
    @Override
    public void init() {
        System.out.println("this is RandomLoadBalance");
    }
}
