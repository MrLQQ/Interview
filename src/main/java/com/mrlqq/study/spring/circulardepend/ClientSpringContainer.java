package com.mrlqq.study.spring.circulardepend;

import com.mrlqq.study.spring.bean.A;
import com.mrlqq.study.spring.bean.B;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.spring.circulardepend
 * @className: ClientSpringContainer
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/13 11:58
 * @version: 1.0
 */
public class ClientSpringContainer {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        A a = context.getBean("a",A.class);
        B b = context.getBean("b",B.class);
    }
}
