package com.mrlqq.study.jvm.oom;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.jvm.oom
 * @className: MetaspaceOOMTest
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/16 23:28
 * @version: 1.0
 *
 * jvm参数调整：
 * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 *
 * java8及之后的版本使用Metaspace来代替永久代
 *
 * Metaspace是方法区在HotSpot中的实现，它与永久代最大的区别在于：Metaspace并不在虚拟机内存中而是使用本地内存
 * 也即在java8中，classe metadata（the virtual machines internal presentation of java class）,被存储在叫做
 * Metaspace的native memory
 *
 * 永久代（Java8后被元空间Metaspace取代了）存放了以下信息：
 *   虚拟机加载的类信息
 *   常量池
 *   静态变量
 *   即时编译后的代码
 *
 * 模拟Metaspace空间溢出，我们不断生成类往元空间灌，类占据的空间总是会超过Metaspace指定的空间大小的
 *
 * OutOfMemoryError: Metaspace
 *
 */
public class MetaspaceOOMTest {

    static class OOMTest{ }

    public static void main(String[] args) {

        // 模拟计数多少次后发生异常
        int i = 0;
        try {
            while (true){
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o,args);
                    }
                });
                enhancer.create();
            }
        }catch (Throwable e){
            System.out.println("***********多少次后发生了异常："+i);
            e.printStackTrace();
        }
    }
}
