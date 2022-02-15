package com.mrlqq.study.thread;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.thread
 * @className: SemaphoreDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/15 20:25
 * @version: 1.0
 *
 * 信号量主要用于两个目的，一个是用于共享资源的互斥使用，另一个用于并发线程数的控制
 *
 * 模拟抢车位,6辆车抢3个车位,当车位被占了,处于等待,若是有车位空出来,就让下一个车进入
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 默认非公平锁
        Semaphore semaphore = new Semaphore(3); // 三个停车位

        for (int i = 1; i < 6; i++) {
            int time = new Random().nextInt(3)+1;
            new Thread(()->{
                try {
                    // 抢占车位
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
                    TimeUnit.SECONDS.sleep(time);
                    System.out.println(Thread.currentThread().getName()+"\t 停车"+time+"秒后离开车位");
                    // 释放车位
                    semaphore.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            },String.valueOf(i).toString()).start();
        }

    }
}
