package com.mrlqq.study.spring.bean;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.spring.circulardepend
 * @className: B
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/13 12:11
 * @version: 1.0
 */
public class B {
    A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
        System.out.println("---B created success");
    }
}
