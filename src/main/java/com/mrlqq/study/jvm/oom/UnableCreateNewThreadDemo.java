package com.mrlqq.study.jvm.oom;

import java.util.concurrent.TimeUnit;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.jvm.oom
 * @className: UnableCreateNewThreadDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/16 22:41
 * @version: 1.0
 *
 * 高并发请求服务器时，经常会出现如下异常：java.lang.OutOfMemoryError:unable to create new native thread
 * 准确的讲该native thread 异常于对应的平台有关
 *
 * 导致原因：
 *  1.你的应用创建了太多线程了，一个应用进程创建多个线程，超过系统承载极限
 *  2.你的服务器并不允许你的应用程序创建这么多线程，Linux系统默认允许单个进程可以创建的线程数1024个，
 *    你的应用创建超过这个数量，机会报java.lang.OutOfMemoryError:unable to create new native thread
 *
 * 解决方法：
 * 1.想办法降低你的应用程序创建线程的数量，分析应用是否真的需要创建这么多线程，如果不是，改代码将建成数量讲到最低
 * 2.对于有的应用，确实需要创建多个线程，远超过Linux系统默认的1024个线程的限制，可以通过修改Linux服务器配置，扩大Linux默认限制
 *
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for (int i = 1; ; i++){
            System.out.println("********i="+i);
            new Thread(()->{
                try {
                   TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            },""+i).start();
        }
    }
}
