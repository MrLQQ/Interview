package com.mrlqq.study.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.thread
 * @className: CyclicBarrierDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/15 20:09
 * @version: 1.0
 *
 * 测试CyclicBarrier
 * 当找到7颗龙珠就能召唤神龙
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("\t 召唤神龙");
        });

        for (int i = 1; i <= 8; i++) {
            final int TempInt = i;
            int finalI = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"找到第"+ TempInt +"个龙珠");

                try {
                    // 执行完成进行等待
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i).toString()).start();
        }

    }
}
