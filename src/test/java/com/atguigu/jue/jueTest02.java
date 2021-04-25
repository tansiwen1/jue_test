package com.atguigu.jue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author:谭思文
 * @createTime:2021/4/24 9:23
 * @version:1.0
 */

class Test {
    private Integer a = 0;

    public synchronized void add() throws InterruptedException {
        if (a != 0){
            this.wait();

        }
         a++;
         System.out.println(Thread.currentThread().getName()+"\t"+a);
         this.notify();
    }

    public synchronized void del() throws InterruptedException {
        if (a == 0){
            this.wait();

        }
        a--;
        System.out.println(Thread.currentThread().getName()+"\t"+a);
        this.notify();
    }
}

public class jueTest02 {
    public static void main(String[] args) {
        Test test = new Test();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    test.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    test.del();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();


    }
}
