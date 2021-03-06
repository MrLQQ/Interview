package com.mrlqq.study.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.thread
 * @className: ReadWriteLockDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/15 18:17
 * @version: 1.0
 *
 * 多个线程通过读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行，
 * 但是，如果有一个线程想去写共享资源，就不应该再有其他线程可以对该资源进行读和写
 *
 *总结：
 *      读-读 能共存
 *      读-写 不能功勋
 *      写-写 不能共存
 */

// 资源类
class MyCache{

    private volatile Map<String, Object> map = new HashMap<>();

    // 创建一个读写锁
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value){
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在写入：" + key);
            // 暂停一会
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+" \t 写入完成："+value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwLock.writeLock().unlock();
        }

    }

    public void get(String key){
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在读取");
            // 暂停一会
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+" \t 读取完成："+result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock();
        }
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        // 5个写线程
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                myCache.put(tempInt+"",tempInt+"");

            },String.valueOf(i).toString()).start();

        }

        // 5个读线程
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                myCache.get(tempInt+"");

            },String.valueOf(i).toString()).start();

        }
    }
}
