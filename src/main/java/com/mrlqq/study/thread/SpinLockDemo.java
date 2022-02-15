package com.mrlqq.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.thread
 * @className: SpinLockDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/15 17:54
 * @version: 1.0
 *
 * 实现一个自旋锁
 * 自旋锁好处：循环比较获取知道成功为止，没有类似wait的阻塞
 *
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock方法自己持持有锁5秒中，
 * B随后进来后发现当前有线程持有锁，不是null，所以只能通过自选等待，知道A释放锁后B随后抢到
 *
 */
public class SpinLockDemo {

    // 原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in");

        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+ "\t invoked myUnLock()");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(()->{
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        },"AA").start();

        // main线程暂停一秒钟，保证AA线程先启动
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            spinLockDemo.myLock();
            spinLockDemo.myUnLock();
        },"BB").start();
    }
}
