package com.mrlqq.study.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.thread
 * @className: CountDownLatchDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/15 19:25
 * @version: 1.0
 *
 * 测试CountDownLatch 倒计时计算器
 * 班长需要等待六个同学都离开教师了才能锁门
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        // 秦国统一
        countryDown();

        // 班长最后锁门
        closeDoor();
    }

    /**
     * 模拟秦国灭了其他国家后 在统一
     * 使用 枚举 作为各个国家的数据库
     * @throws InterruptedException
     */
    private static void countryDown() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 国被灭");
                // 一个线程执行完成就使countDownLatch减一（countDown）
                countDownLatch.countDown();
            },CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }

        // 当前线程等待直到锁存器倒计时到零
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t 秦国统一");
    }


    /**
     * 班长关门案例 CountDownLatch
     * @throws InterruptedException
     */
    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 上完自习，离开教室");
                // 一个线程执行完成就使countDownLatch减一（countDown）
                countDownLatch.countDown();
            },String.valueOf(i).toString()).start();
        }

        // 当前线程等待直到锁存器倒计时到零
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t 班长离开教室，并锁了门");
    }
}


