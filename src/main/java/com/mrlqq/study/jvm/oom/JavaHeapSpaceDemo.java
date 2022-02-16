package com.mrlqq.study.jvm.oom;

import java.util.Random;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.jvm.oom
 * @className: JavaHeapSpaceDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/16 21:57
 * @version: 1.0
 *
 * 堆溢出
 * OutOfMemoryError: Java heap space
 *
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {

        String str = "mrlqq";

        // 启动前，修改jvm初始化堆的最大堆的大小 -Xms10m -Xmx10m
        while (true){
            str+=str+new Random().nextInt(111111111) + new Random().nextInt(222222222);
            str.intern();
        }
    }
}
