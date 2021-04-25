package com.atguigu.jue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author:谭思文
 * @createTime:2021/4/25 12:15
 * @version:1.0
 */

class ShareResource {
    int flag = 1;//默认1对应A系统，2对应B系统，3对应C系统。。。。。

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() throws InterruptedException
    {
        lock.lock();
        try
        {
            //1 判断
            while(flag != 1)
            {
                condition1.await();
            }
            //2 干活
            for (int i = 1; i <=5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3 通知
            flag = 2;
            condition2.signal();
        }finally {
            lock.unlock();
        }
    }

    public void print10() throws InterruptedException
    {
        lock.lock();
        try
        {
            //1 判断
            while(flag != 2)
            {
                condition2.await();
            }
            //2 干活
            for (int i = 1; i <=10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3 通知
            flag = 3;
            condition3.signal();
        }finally {
            lock.unlock();
        }
    }

    public void print15() throws InterruptedException
    {
        lock.lock();
        try
        {
            //1 判断
            while(flag != 3)
            {
                condition3.await();
            }
            //2 干活
            for (int i = 1; i <=15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3 通知
            flag = 1;
            condition1.signal();
        }finally {
            lock.unlock();
        }
    }

}

public class jueTest04 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
         new Thread(() -> {
             try {
                 shareResource.print5();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }, "A").start();
         new Thread(() -> {
             try {
                 shareResource.print10();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }, "B").start();
         new Thread(() -> {
             try {
                 shareResource.print15();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }, "C").start();

    }

}
