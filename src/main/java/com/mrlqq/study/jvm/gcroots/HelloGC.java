package com.mrlqq.study.jvm.gcroots;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.jvm.gcroots
 * @className: HelloGC
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/16 3:32
 * @version: 1.0
 */
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("hello");
        Thread.sleep(Integer.MAX_VALUE);

        /*
        // 返回jvm的内存总量
        long totalMemory = Runtime.getRuntime().totalMemory();
        // 返回jvm试图是哦那个的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("TotalMemory(-xms)="+totalMemory+"(字节)、"+(totalMemory/(double) 1024/1024)+"MB");
        System.out.println("MaxMemory(-xmx)="+maxMemory+"(字节)、"+(maxMemory/(double) 1024/1024)+"MB");
        */

    }
}
