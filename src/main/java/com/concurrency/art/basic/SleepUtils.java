package com.concurrency.art.basic;

import java.util.concurrent.TimeUnit;

/**
 * TimeUnit使用
 * since 1.5
 */
public class SleepUtils {
    public static final void second(long seconds) {
        try {
            //TimeUnit.SECONDS.sleep(seconds);
            TimeUnit.MILLISECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
