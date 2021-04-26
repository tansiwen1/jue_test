package com.atguigu.jue;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author:谭思文
 * @createTime:2021/4/26 9:09
 * @version:1.0
 */


class KillLock{



}

public class jueTest07 {
    public static void main(String[] args) {

        final  Object objectLockA = new Object();
        final  Object objectLockB = new Object();

        new Thread(() -> {
            synchronized (objectLockA){
                 System.out.println(Thread.currentThread().getName()+"\t"+"A锁希望获得B锁");
                try{
                    TimeUnit.SECONDS.sleep(10);
                } catch(Exception e){
                    e.printStackTrace();
                }
                synchronized (objectLockB){
                    System.out.println(Thread.currentThread().getName()+"\t"+"获得B锁");
                }
            }
            // 休息几秒钟

        }, "A").start();
        new Thread(() -> {
            synchronized (objectLockB){
                System.out.println(Thread.currentThread().getName()+"\t"+"B锁希望获得A锁");
                // 休息几秒钟
                try{
                    TimeUnit.SECONDS.sleep(10);
                } catch(Exception e){
                    e.printStackTrace();
                }
                synchronized (objectLockA){
                    System.out.println(Thread.currentThread().getName()+"\t"+"获得A锁");
                }
            }

        }, "A").start();
    }
}
