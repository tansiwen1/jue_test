package com.atguigu.jue;

import java.awt.print.Book;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirCondition //空调，资源类
{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public synchronized void increment() throws InterruptedException
    {
        while (number==0){
            number++;
             System.out.println(Thread.currentThread().getName()+"\t"+number);

//             this.notify();
        }
        while (number==1){
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);

//            this.notify();
        }
    }


}


/**
 * @auther zzyy
 * @create 2020-03-26 11:41
 *
 * 有一个初始值为零的变量，现有两个线程对该变量操作，
 * 实现一个线程对变量加1，实现一个线程对变量减1,交替来10次
 *
 * 1 高内聚低耦合的前提下，线程       操作    资源类
 * 2 判断、干活、通知。
 * 3 防止虚假唤醒，用while
 */
public class ProdConsumerDemo
{

    static Object objectLock = new Object();
    static Book b1 = new Book();


    public static void main(String[] args) {
        AirCondition airCondition = new AirCondition();
        new Thread(() -> {
            try {for (int i = 1; i <= 10; i++)
                airCondition.increment();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

//        new Thread(() -> {
//            try {for (int i = 1; i <= 10; i++)
//                airCondition.increment();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, "B").start();
    }
}
