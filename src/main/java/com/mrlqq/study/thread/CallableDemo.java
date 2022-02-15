package com.mrlqq.study.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.thread
 * @className: CallableDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/15 20:47
 * @version: 1.0
 *
 * 测试Callable多线程
 *
 * futureTask如果没有算完,就获取结果,会导致主线程在阻塞;
 * futureTask结果缓存,线程调用同一个任务,可直接获取上次结果;
 */
class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("come in Callable");
        return 1024;
    }
}
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask futureTask1 = new FutureTask<Integer>(new MyThread());
        FutureTask futureTask2 = new FutureTask<Integer>(new MyThread());
        FutureTask futureTask3 = new FutureTask<Integer>(new MyThread());
        new Thread(futureTask1,"AA").start();
        new Thread(futureTask2,"BB").start();
        new Thread(futureTask3,"CC").start();

        while (futureTask1.isDone() && futureTask2.isDone() && futureTask3.isDone()){

        }
        // 如果没算完 强求结果,会导致主角线程阻塞
        int result = (Integer) futureTask3.get()+(Integer) futureTask2.get()+(Integer) futureTask3.get();

        System.out.println("result = " + result);

    }
}
