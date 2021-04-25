package com.atguigu.jue;

/**
 * @description:
 * @author:谭思文
 * @createTime:2021/4/25 12:58
 * @version:1.0
 */


class Test2{
    private Integer number = 0 ;

    public synchronized void add(){
        while (number!=0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number++;
         System.out.println(Thread.currentThread().getName()+"\t"+number);


    }

    public synchronized void del(){
        while (number!=1){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number--;
         System.out.println(Thread.currentThread().getName()+"\t"+number);
         this.notifyAll();
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
public class jueTest06 {
    public static void main(String[] args) {
        Test2 test2 = new Test2();
        new Thread(() -> { test2.add(); }, "A").start();
        new Thread(() -> { test2.del(); }, "B").start();

    }
}
