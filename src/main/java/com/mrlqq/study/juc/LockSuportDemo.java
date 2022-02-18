package com.mrlqq.study.juc;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.juc
 * @className: LockSuportDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/18 13:24
 * @version: 1.0
 *
 * LockSupport是用来创建锁和其他同步类的基本线程阻塞原语
 *
 * LockSupport 类使用了一种名为Permit（许可）的概念来做的阻塞和唤醒线程的功能，每个线程都有一个许可（permit），
 * permit只有两个指1和零，默认是零。
 * 可以把许可堪称是一种（0，1）信号量（Semaphore），但与Semaphore不同的是，许可的累加上限是1.
 *
 * LockSupport提供park和unpark方法实现阻塞线程和解除线程阻塞
 *
 *
 */
public class LockSuportDemo {
    static  Object objectLock = new Object();
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        lockSupportParkUnpark();
    }

    /**
     * 优势
     * 不需要创建一个锁，可以直接调用LockSupport
     *
     * 即使是先执行B线程unpack，先发放通行证，3秒钟后执行A线程再park，也不影响程序的正常执行
     */
    private static void lockSupportParkUnpark(){

        Thread a= new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t -------come in");
            // 被阻塞等待通知，需要许可证
            LockSupport.park();
            LockSupport.park();
            System.out.println(Thread.currentThread().getName()+"\t --------被唤醒");
        },"A");
        a.start();


        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Thread b = new Thread(()->{
            // 唤醒，对a发放通行证
            LockSupport.unpark(a);
            try {
               TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            LockSupport.unpark(a);
            System.out.println(Thread.currentThread().getName()+"\t------通知");
        },"B");
        b.start();
    }


    /**
     * 效果与synchronized的一样
     *
     * 1.线程先要获得并持有锁，必须在锁块（synchronized或lock）中
     * 2.必须要先等待后唤醒，线程才能够被唤醒
     */
    private static void lockAwaitSignal(){
        new Thread(()->{
            try {
               TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
          lock.lock();
          try {
              System.out.println(Thread.currentThread().getName()+"\t -------come in");
              condition.await();
              System.out.println(Thread.currentThread().getName()+"\t --------被唤醒");
          }catch (Exception e){
              e.printStackTrace();
          }finally {
              lock.unlock();
          }  
        },"A").start();

        new Thread(()->{
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName()+"\t------通知");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"B").start();
    }
    
    
    /**
     * 要求A线程等待3秒钟，3秒钟后B线程唤醒A线程继续工作
     * 以下异常情况：
     * 2.wait方法和notify方法，两个都去掉同步代码快后看运行运行效果
     *      2.1 异常情况
     *          Exception in thread "B" Exception in thread "A" java.lang.IllegalMonitorStateException
     *      2.2 结论
     *          Object类中的wait、notify、notifyAll用于多线程等待和唤醒的方法，都必须再synchronized内部执行（必须用到关键字synchronized）
     * 3.将notify放在wait方法前面先执行，A先notify，3秒钟后B再wait
     *      3.1 程序一直无法结束
     *      3.2 结论
     *          先wait再notify、notifAall方法，等待中的线程才会被唤醒，否则无法唤醒
     *
     */
    private static void synchronizedWaitNotify() {
        new Thread(()->{
            // synchronized (objectLock){
                System.out.println(Thread.currentThread().getName()+"\t ------come in");
                try {
                    objectLock.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t ------被唤醒");
            // }
        },"A").start();

        new Thread(()->{
            // synchronized (objectLock){
                objectLock.notify();
                System.out.println(Thread.currentThread().getName()+"\t ------通知");
            // }
        },"B").start();
    }
}
