package com.mrlqq.study.jvm.ref;

import java.lang.ref.WeakReference;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.jvm.ref
 * @className: WeakReferenceDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/16 20:50
 * @version: 1.0
 *
 * 弱引用
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> objectWeakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(objectWeakReference.get());

        o1 = null;
        System.gc();
        System.out.println("==========");

        System.out.println(o1);
        System.out.println(objectWeakReference.get());
    }
}
