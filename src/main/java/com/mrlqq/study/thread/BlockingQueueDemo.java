package com.mrlqq.study.thread;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.thread
 * @className: BlockingQueueDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/15 22:41
 * @version: 1.0
 *
 * ArrayBlockingQueue: 是一个基于数组结构的有界阻塞队列,此队列按FIFO原则对元素进行排序.
 * LinkedBlockingQueue: 一个基于链表结构的有界阻塞队列(最大为Integer.MAX_VALUE),此队列按FIFO排序元素,吞吐量通常要高于ArrayBlockingQueue
 * SynchronousQueue: 一个基于不存储元素的阻塞队列(单个元素的队列),每个插入操作必须等到另一个线程调用移除操作,否则插入操作一直处于阻塞状态,
 *                  吞吐量通常要高于
 *
 * 1 队列 FIFO
 * 2 阻塞队列
 *   2.1 阻塞队列有没有好的一面
 *      我们不需要关心什么时候阻塞线程,什么时候唤醒线程,因为BlockingQueue会自动帮我们完成,
 *   2.2 不得不阻塞,你如何管理
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.offer("a",2L, TimeUnit.SECONDS);
        blockingQueue.offer("a",2L, TimeUnit.SECONDS);
        blockingQueue.offer("a",2L, TimeUnit.SECONDS);
        blockingQueue.offer("a",2L, TimeUnit.SECONDS);
    }
}
