package com.mrlqq.study.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.jvm.oom
 * @className: GCOverheadDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/16 22:11
 * @version: 1.0
 *
 * OOM -> GCOverhead
 * OutOfMemoryError: GC overhead limit exceeded
 *
 * JVM参数配置： -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=1m
 *
 * GC回收时间过长时会抛出OOM。过长的定义是，超过98%的时间用来做GC并且回收了不到2%的堆内存
 * 连续多次GC都只是回收了不到2%的极端情况下才会抛出。加入不跑出GC Overhead limit错误会发生什么情况呢？
 * 那就是GC清理的那么点内存很快会再次填满，迫使GC再次执行，这样就形成了了恶性循环，
 * CPU使用率一直100%，而GC去没有任何成果
 *
 */
public class GCOverheadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();

        try {
            while (true){
                list.add(String.valueOf(++i).intern());
            }
        }catch (Throwable e)
        {
            System.out.println("============i:"+i);
            e.printStackTrace();
            throw e;
        }
    }
}
