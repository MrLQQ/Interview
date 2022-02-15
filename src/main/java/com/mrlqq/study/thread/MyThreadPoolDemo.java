package com.mrlqq.study.thread;

import java.util.concurrent.*;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.thread
 * @className: MyThreadPoolDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/15 22:31
 * @version: 1.0
 *
 * 线程池
 * 线程池做的工作主要是控制运行的线程的数量,处理过程中将任务放入队列,然后在线程创建后启动这些任务,如果线程数量超过了最大数量,
 * 超出的线程排队等候,等其他线程执行完毕,再从队列中取出任务来执行
 *
 * 特点:线程复用,控制最大并发数,管理线程.
 *
 * 第一:降低资源消耗.通过重复利用已创建的线程降低线程创建和销毁造成的消耗.
 * 第二:提高响应速度.当任务达到时,任务可以不需要等到线程创建就能立即执行.
 * 第三:提高线程的客观理性.线程是稀缺资源,如果无限制的创建,不仅会消耗系统资源,还会降低系统的稳定性,
 *      使用线程池可以进行统一的分配,调优和监控
 *
 * 第4种获得使用java多线程的方式，线程池
 *
 * 七大参数：
 * int corePoolSize：核心线程数，是在线程池种常驻的线程数
 * int maximumPoolSize：
 * long keepAliveTime：
 * TimeUnit unit：
 * BlockingQueue<Runnable> workQueue：
 * ThreadFactory threadFactory：
 * RejectedExecutionHandler handler：
 *
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        // System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                12,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 1; i <= 20; i++) {
                threadPool.execute(()->{
                    try {
                       TimeUnit.MILLISECONDS.sleep(1);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

    private static void ThreadPoolInit() {
        // 适用于执行长期的任务，性能好很多
        // ExecutorService threadPool = Executors.newFixedThreadPool(5); // 一池5个线程

        // 一个任务一个任务执行的场景
        // ExecutorService threadPool = Executors.newSingleThreadExecutor(); // 一池1个线程

        // 适用于执行很多短期异步的小程序或者负载叫轻的服务器
        ExecutorService threadPool = Executors.newCachedThreadPool(); // 一池N个线程

        // 模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
