package com.mrlqq.study.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.thread
 * @className: CASDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/13 17:18
 * @version: 1.0
 *  1、CAS是什么 ==> compareAndSet
 *      比较并交换
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println("atomicInteger.compareAndSet(5,2022) = " + atomicInteger.compareAndSet(5, 2022));
        System.out.println("atomicInteger.compareAndSet(5,1024) = " + atomicInteger.compareAndSet(5, 1024));
        System.out.println("atomicInteger.get() = " + atomicInteger.get());
    }
}
