package com.mrlqq.study.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.thread
 * @className: SynchronousQueueDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/15 23:22
 * @version: 1.0
 *
 * SynchronousQueue 同步队列
 *
 * 与其他BlockingQueue不同,SynchronousQueue是一个不存储元素的BlockingQueue
 * 每一个put操作都要必须等待一个take操作,否则不能继续添加元素,反之亦然.
 *
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"\t put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"\t put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+" \t blockingQueue.take() = " + blockingQueue.take());
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+" \t blockingQueue.take() = " + blockingQueue.take());
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+" \t blockingQueue.take() = " + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();
    }
}
