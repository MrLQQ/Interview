package com.mrlqq.study.jvm.oom;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.jvm.oom
 * @className: StackOverflowErrorDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/16 21:51
 * @version: 1.0
 *
 * OOM -> java.lang.StackOverflowError
 * 栈溢出
 * 由于死递归产生
 */
public class StackOverflowErrorDemo {
    public static void main(String[] args) {
        // java.lang.StackOverflowError
        stackOverflowError();
    }

    private static void stackOverflowError() {
        stackOverflowError();
    }

}
