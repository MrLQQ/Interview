package com.mrlqq.study.thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.thread
 * @className: ContainerNotSafeDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/13 20:18
 * @version: 1.0
 * 集合类不安全问题
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {

        mapNotSafe();

        setNotSafe();

        listNotSafe();

    }

    /**
     * HashMap线程不安全
     * 解决： 使用 new ConcurrentHashMap<>();
     *       使用
     */
    private static void mapNotSafe() {
        // Map<String, String> map = new ConcurrentHashMap<>();
        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());

        for (int i = 1; i <= 30; i++) {
            new Thread(()->{
                map.put(UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    /**
     * HashSet线程不安全
     * 解决：使用 new CopyOnWriteArraySet
     *      使用 Collections.synchronizedSet(new HashSet<>())
     */
    private static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();
        // Set<String> set = Collections.synchronizedSet(new HashSet<>());

        for (int i = 1; i <= 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    /**
     * ArrayList 线程不安全
     */
    private static void listNotSafe() {
        /**
         * ArrayList线程不安全
         *
         * 解决： 1. new Vector<>();
         *       2. collections.SynchronizedList(new ArrayList());
         *       3. new CopyOnWriteArrayList();
         */
        // List<String> list = new CopyOnWriteArrayList<>();
        // List<String> list = new Vector<>();
        List<String> list = Collections.synchronizedList(new ArrayList<>());

        for (int i = 1; i <= 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
