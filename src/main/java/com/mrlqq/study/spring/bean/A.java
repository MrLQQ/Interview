package com.mrlqq.study.spring.bean;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.spring.circulardepend
 * @className: A
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/13 12:12
 * @version: 1.0
 */
public class A {
    B b;

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
        System.out.println("---A created success");
    }

}
