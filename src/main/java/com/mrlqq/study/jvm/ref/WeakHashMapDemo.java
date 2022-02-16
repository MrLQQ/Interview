package com.mrlqq.study.jvm.ref;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.jvm.ref
 * @className: WeakHashMapDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/16 21:03
 * @version: 1.0
 *
 * WeakHashMap测试
 *
 * 当WeakHashMap中的某个键失效的时候，就会被回收
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {

        // 常规HashMap
        // myHashMap();

        // WeakHashMap
        myWeakHashMap();
    }

    private static void myHashMap(){
        HashMap<Integer, String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "hashMap";

        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map+"\t"+map.size());
    }

    private static void myWeakHashMap(){
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "WeakHashMap";

        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map+"\t"+map.size());
    }
}
