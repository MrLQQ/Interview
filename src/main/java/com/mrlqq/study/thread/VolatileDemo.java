package com.mrlqq.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData{
    // 增加volation
    volatile int number = 0;

    public void addT060(){
        this.number = 60;
    }

    // 请注意，此时number前面是加了volatile关键字修改时的，volatile不保证原子性
    public void addPlusPlus(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addMyAtomic(){
        atomicInteger.getAndIncrement();
    }
}


/**
 * @projectName: Interview
 * @package: com.mrlqq.study.thread
 * @className: VolatileDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/13 14:54
 * @version: 1.0
 *
 * 1、ya 验证volatile的可见性
 *      1.1 假如int number = 0;变量之前根本没有添加volatile关键字修饰
 *      1.2 添加了volatile，可以解决可见性。
 *
 * 2、验证volatile不保证原子性
 *      2.1 原子性是指什么意思？
 *              不可分割、完整性的，也即某个线程正在做某个具体业务的时候，中间不可以被其他线程加载或者被分割。
 *              需要整体完整要么同时成功，要么同时失败。
 *      2.2 volatile不保证原子性演示
 *      2.3 如何解决原子性？
 *          * 加sunc
 *          * 使用我们的juc下面的AtomicInteger
 */
public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();

        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }
            },String.valueOf(i)).start();
        }

        // 需要等待上面20个线程都全部计算完成后，再用main线程缺德最红的记过值看是多少
        while (Thread.activeCount()>2){
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName()+"\t int finally number value:"+myData.number);
        System.out.println(Thread.currentThread().getName()+"\t AtomicInteger finally number value:"+myData.atomicInteger.intValue());
    }


    /**
     * volatile 可以保证可见性，即使通知其他线程，主物理内存的值已经被修改。
     */
    private static void seeOkByVolatile() {
        // 资源类
        MyData myData = new MyData();

        // 第一个线程
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            // 暂停一会儿线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addT060();
            System.out.println(Thread.currentThread().getName()+"\t updated number value:"+myData.number);
        },"AAA").start();

        // 第二个线程
        while(myData.number==0){
            // main线程就一直在这里等待循环，直到number值不在等待
        }
        System.out.println(Thread.currentThread().getName()+"\t come in"+" value="+myData.number);
    }
}
