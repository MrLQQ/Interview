package com.mrlqq.study.thread;

import javax.swing.plaf.SpinnerUI;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{
    public synchronized  void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoked sendSms()");
        sendEmail();
    }

    public synchronized  void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoked sendEmail()");
    }

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked get()");
            set();
        }finally {
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t invoked set()");
        }finally {
            lock.unlock();
        }
    }
}

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.thread
 * @className: ReenterLockDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/15 17:28
 * @version: 1.0
 * 可重入锁也叫递归锁
 *
 * 指的是同一线程外层函数获得锁之后，内层递归函数仍能获得该锁的代码
 * 在同一线程在外层方法获得锁的时候，在进入内层方法会自动获得锁
 *
 * 也就是说，线程可以进入任何一个他已经有用锁同步的代码块
 * case one synchronized是典型的可重入锁
 * t1	 invoked sendSms()
 * t1	 invoked sendEmail()
 * t2	 invoked sendSms()
 * t2	 invoked sendEmail()
 *
 * case two ReentrantLock也是一个可重入锁
 * t1	 invoked get()
 * t1	 invoked set()
 * t2	 invoked get()
 * t2	 invoked set()
 */
public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            try {
                // 测试synchronized锁
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                // 测试synchronized锁
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=====================");

        new Thread(()->{
            try {
                // 测试ReentrantLock锁
                phone.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t3").start();

        new Thread(()->{
            try {
                // 测试ReentrantLock锁
                phone.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t4").start();
    }
}
