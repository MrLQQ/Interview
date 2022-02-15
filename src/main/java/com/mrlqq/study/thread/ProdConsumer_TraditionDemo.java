package com.mrlqq.study.thread;

import org.springframework.ui.context.Theme;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * @projectName: Interview
 * @package: com.mrlqq.study.thread
 * @className: ProdConsumer_TraditionDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/15 23:34
 * @version: 1.0
 *
 * 生产者消费者传统版
 *
 * 题目:一个初始值为零的变量,两个线程对其变量交替操作,一个加1一个减1,进行5轮
 *
 * 1. 线程 操作 资源类
 * 2. 判断 干活 通知
 * 3. 防止虚假话唤醒
 */


/***
 * 资源类
 */
class ShareData{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition connection = lock.newCondition();

    public void increment() throws Exception{
        lock.lock();
        try {
            // 1.判断
            while(number != 0){
                // 等待,不能生产
                connection.await();
            }
            // 2.干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            // 3.通知唤醒
            this.connection.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception{
        lock.lock();
        try {
            // 1.判断
            while(number == 0){
                // 等待,不能生产
                connection.await();
            }
            // 2.干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            // 3.通知唤醒
            this.connection.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }



}
public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
    }
}
