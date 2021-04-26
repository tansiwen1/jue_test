package com.atguigu.jue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author:谭思文
 * @createTime:2021/4/23 20:32
 * @version:1.0
 */

class Ticket {
    private Integer number = 30;

    Lock lock = new ReentrantLock();

    public void sale() {

        lock.lock();
        try {
            if (number > 0) {

                System.out.println(Thread.currentThread().getName() + "\t" + "卖出第" + (number--) + "\t" +"还剩" +number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class jueTest {
    public static void main(String[] args) {
        System.out.println("1sds11");
        System.out.println("acadfsaf");
        Ticket ticket = new Ticket();
        new Thread(() -> { for (int i = 1; i <= 31; i++) ticket.sale(); }, "A").start();
        new Thread(() -> { for (int i = 1; i <= 31; i++) ticket.sale(); }, "B").start();
        new Thread(() -> { for (int i = 1; i <= 31; i++) ticket.sale(); }, "C").start();
        new Thread(() -> { for (int i = 1; i <= 31; i++) ticket.sale(); }, "D").start();


    }
}
