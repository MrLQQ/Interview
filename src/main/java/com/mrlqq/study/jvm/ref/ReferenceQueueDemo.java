package com.mrlqq.study.jvm.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.jvm.ref
 * @className: ReferenceQueueDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/16 21:13
 * @version: 1.0
 *
 * 引用队列
 *
 * 引用的对象被垃圾回收后的时候 会存放到引用队列中
 */
public class ReferenceQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o1,referenceQueue);

        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("===========");

        o1 = null;
        System.gc();
        Thread.sleep(500);

        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
    }
}
