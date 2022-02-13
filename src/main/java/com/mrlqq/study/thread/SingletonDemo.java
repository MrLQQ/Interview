package com.mrlqq.study.thread;

import java.text.SimpleDateFormat;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.thread
 * @className: SingletonDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/13 16:48
 * @version: 1.0
 */
public class SingletonDemo {

    private static volatile SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法 SingletonDemo()");
    }

    // DCL(Double Check Lock)双重校验锁
    private static SingletonDemo getInstance(){
        if (instance == null){
            synchronized (SingletonDemo.class){
                if (instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {

        // // 单线程（main线程的操作）
        // System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        // System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        // System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        System.out.println("================");

        // 并发多线程后，情况发生了很大的变化
        for (int i = 1; i <=10; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
