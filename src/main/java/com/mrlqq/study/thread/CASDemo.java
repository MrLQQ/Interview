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
 *  1、CAS是什么 ==> CompareAndSwap
 *      比较并交换，真实值和期望值相同就更改，不相同就不更改，返回布尔值
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println("atomicInteger.compareAndSet(5,2022) = " + atomicInteger.compareAndSet(5, 2022));
        System.out.println("atomicInteger.compareAndSet(5,1024) = " + atomicInteger.compareAndSet(5, 1024));
        System.out.println("atomicInteger.get() = " + atomicInteger.get());
    }
}
