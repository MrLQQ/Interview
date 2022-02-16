package com.mrlqq.study.jvm.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.jvm.ref
 * @className: PhantomReferenceDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/16 21:29
 * @version: 1.0
 *
 * Java提供了4种引用类型，在垃圾回收的时候，都有自己各自的特点。
 * ReferenceQueue是用来配合引用工作的，没有ReferenceQueue一样可以运行
 *
 * 创建引用的时候可以指定关联的队列，当GC释放对象内存的时候，会将引用加入到引用队列，
 * 如果线程发现某个虚引用已经被加入到引用队列种，那么就可以在引用对象的内存被回收之前采取必要的行动。
 * 这相当于是一种通知机制。
 *
 * 当关联的引用队列种有数据的时候，意味着引用指向的堆内存种的对象被回收。通过这种方式，JVM允许我们在对象被销毁后，
 * 做一些我们自己想做的事情。
 *
 *
 *
 * 虚引用测试
 *
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1,referenceQueue);

        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        o1 = null;
        System.gc();
        Thread.sleep(500);
        System.out.println("================");

        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}
