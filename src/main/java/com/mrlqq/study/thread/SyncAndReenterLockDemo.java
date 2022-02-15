package com.mrlqq.study.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.thread
 * @className: SyncAndReenterLockDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/15 21:08
 * @version: 1.0
 *
 * Sync和Lock的区别
 * 1. Sync是系统关键字;   Lock是juc下的一个方法发
 * 2. Sync是一个非公平锁,; Lock可以设置成公平锁和非公平锁
 * 3. Sync会自动释放锁,不会产生死锁;    Lock需要手动释放锁,否则会产生思索
 * 4. Sync不可以中断;    Lock可以被中断
 * 5. Sync只能随机唤醒一个线程或者唤醒所有线程;   Lock可以精确唤醒某个线程
 *
 * 测试:
 * 多个线程按顺序调用 实现A->B->C三个线程启动,
 * 要求:AA打印5次,BB打印是次,CC打印15次,此过程循环10次
 */

class ShareResource{
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    private int number  = 1;

    public void print5(){
        lock.lock();
        try {
            while (number != 1){
                c1.wait();
            }

            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t 第"+i+"次打印");
            }
            number = 2;
            c2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try {
            while (number != 2){
                c2.wait();
            }

            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t 第"+i+"次打印");
            }
            number = 3;
            c3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            while (number != 3){
                c3.await();
            }

            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t 第"+i+"次打印");
            }
            number = 1;
            c1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

public class SyncAndReenterLockDemo {
    public static void main(String[] args) {

        ShareResource shareResource = new ShareResource();

            new Thread(()->{
                for (int i = 1; i <= 10; i++) {
                    shareResource.print5();
                }
            },"AA").start();

            new Thread(()->{
                for (int i = 1; i <= 10; i++) {
                    shareResource.print10();
                }
            },"BB").start();

            new Thread(()->{
                for (int i = 1; i <= 10; i++) {
                    shareResource.print15();
                }
            },"CC").start();



    }
}
