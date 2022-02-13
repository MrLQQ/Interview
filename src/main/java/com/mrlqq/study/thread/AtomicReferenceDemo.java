package com.mrlqq.study.thread;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
class User{
    String userName;
    int age;
}

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.thread
 * @className: AtomicReferenceDemo
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/13 19:45
 * @version: 1.0
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User zs = new User("张三", 22);
        User ls = new User("李四", 33);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(zs);

        // 张三变李四
        System.out.println("atomicReference.compareAndSet(zs, ls) = " + atomicReference.compareAndSet(zs, ls));
        System.out.println("atomicReference.get().toString() = " + atomicReference.get().toString());
    }
}
