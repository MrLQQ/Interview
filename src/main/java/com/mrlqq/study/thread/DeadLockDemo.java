package com.mrlqq.study.thread;

import java.util.concurrent.TimeUnit;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.thread
 * @className: DeadLockDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/16 2:13
 * @version: 1.0
 *
 * 死锁是指两个或两个以上的进程在过程中，
 * 因争夺资源而造成的一种互相等待的现象，
 * 若无外力干涉那他们都将无法推进下去
 */

class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 自己持有："+lockA+"\t 尝试获得："+lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 自己持有："+lockB+"\t 尝试获得："+lockA);
            }
        }
    }
}

public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA,lockB),"ThreadAAAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"ThreadBBBB").start();

        /**
         * linux    ps -ef|grep xxxx
         * windows下的java运行程序 也有类似于ps的查看进行的命令，但是目前我们需要看到的只有java
         * java为我们提供了jps
         *
         * 先通过jps -l 查看在运行的java的进程号
         * 然后通过jstack <PID> 可以看出来目标java程序的运行的错误
         */
    }
}
