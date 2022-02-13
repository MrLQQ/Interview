package com.mrlqq.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.thread
 * @className: ABAdemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/13 19:11
 * @version: 1.0
 */
public class ABAdemo {
    public static void main(String[] args) {
        System.out.println("=========下面是ABA问题的产生==========");
        AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
        new Thread(() -> {
            System.out.println("线程"+Thread.currentThread().getName()+":"+
                    atomicReference.compareAndSet(100, 101)+"\t"+
                    atomicReference.get());
            System.out.println("线程"+Thread.currentThread().getName()+":"+
                    atomicReference.compareAndSet(101, 100)+"\t"+
                    atomicReference.get());
        }, "t1").start();

        new Thread(() -> {
            //休息一会，让线程t1先执行一遍ABA的问题
            try {
                TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

            System.out.println("线程"+Thread.currentThread().getName()+":"+
                    atomicReference.compareAndSet(100, 2000)+"\t"+
                    atomicReference.get());
        }, "t2").start();

        //休息一会,确保上面两个线程执行完毕
        try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}

        System.out.println("=========下面是ABA问题的解决==========");
        //有点类似于乐观锁
        //初始值设定100，时间戳（版本号=1）
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t 第一次获取版本号"+stamp);

            //休息一会,等待t4获取版本号
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+
                    "\t"+ atomicStampedReference.getReference()+
                    "\t 第2次获取版本号"+atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+
                    "\t"+ atomicStampedReference.getReference()+
                    "\t 第3次获取版本号"+atomicStampedReference.getStamp());
        }, "t3").start();

        //t4和t3最初获取到的版本号一致，
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t 第一次获取版本号"+stamp);

            //休息一会,确保t3完成一次ABA
            try {TimeUnit.SECONDS.sleep(4);} catch (InterruptedException e) {e.printStackTrace();}
            boolean result = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName()+"\t 是否修改成功："+result+"\t 当前真实的版本号："+atomicStampedReference.getStamp()
                    +"\t 当前真实的值："+atomicStampedReference.getReference());
        }, "t4").start();


    }
}
