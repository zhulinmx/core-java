package com.concurrency.art.ch00;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

public class BankWaterService implements Runnable {

    /**
     * 创建四个屏障，处理完之后执行当前类的run方法, BankWaterService是继承了Runnable，指定了this
     */
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(4, this);

    /**
     * 假设只有四个 sheet 页，所以启动 4 个线程
     */
    private ExecutorService executor = Executors.newFixedThreadPool(4);

    /**
     * 保存每个 sheet 计算出的银行流水结果
     */
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<>();

    /**
     * 计算流水
     */
    private void caculate() {
        for (int i = 0; i < 4; i++) {
            executor.execute(() -> {
                // 计算当前 sheet 的银流数据
                int sum = (int) (Math.random() * 1000);
                sheetBankWaterCount.put(Thread.currentThread().getName(), sum);
                try {
                    // 计算完成，插入一个屏障
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }

    @Override
    public void run() {
        int result = 0;
        // 汇总每个 sheet 计算出来的结果
        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result += sheet.getValue();
            System.out.println(sheet.getKey()+ " sheet："+sheet.getValue());
        }
        // output
        sheetBankWaterCount.put("result", result);
        System.out.println("result="+ result);
    }


    public static void main(String[] args) {
        BankWaterService bankWaterService = new BankWaterService();
        bankWaterService.caculate();
    }
}
