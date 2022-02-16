package com.mrlqq.study.jvm.ref;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.jvm.ref
 * @className: StrongReferenceDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/16 20:32
 * @version: 1.0
 *
 * 强引用
 * 即使是出现OOM 也不会进行回收
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {

        Object obj1 = new Object(); // 这样定义的默认就是强引用
        Object obj2 = obj1; // obj2引用赋值
        obj1 = null;    // 置空
        System.gc();
        System.out.println(obj2);
    }
}
