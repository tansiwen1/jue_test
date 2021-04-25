package com.atguigu.jue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author:谭思文
 * @createTime:2021/4/25 12:26
 * @version:1.0
 */
class Resource {
    private Integer flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print() {
        lock.lock();
        try {


            while (flag == 1) {

                condition2.await();
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t" + i);
                }
                flag = 2;
                condition2.signal();
                condition1.await();

            }

            while (flag == 2) {

                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t" + i);
                }
                flag = 3;
                condition3.signal();
                condition2.await();


            }

            while (flag ==3 ){
                while (flag!=3){
                    condition3.await();
                }
                for (int i = 0; i < 15; i++) {
                    System.out.println(Thread.currentThread().getName() + "\t" + i);
                }
                flag = 1;
                condition3.await();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class jueTest05 {
    public static void main(String[] args) {
        Resource resource = new Resource();
        new Thread(() -> { for (int i = 1; i <= 10; i++) resource.print(); }, "A").start();
        new Thread(() -> { for (int i = 1; i <= 10; i++) resource.print(); }, "B").start();
        new Thread(() -> { for (int i = 1; i <= 10; i++) resource.print(); }, "C").start();
    }


}
