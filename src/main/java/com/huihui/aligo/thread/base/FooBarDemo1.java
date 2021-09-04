package com.huihui.aligo.thread.base;

/**
 * leetcode题：交替打印FooBar
 * 方案：synchronized + Object.wait()、Object.notify() + flag
 *
 * 思路：1~3方案的基本思路实际上都差不多：
 *      加锁（无论什么锁）目的都是防止当前线程执行是，其他线程也在执行
 *      一个共享变量 flag作为线程间的数据通信，控制着线程的执行逻辑
 *      当flag为当前线程的期望值时，执行逻辑，唤醒其他线程，然后等待；当flat不为当前线程的期望值时，一直等待
 * @author minghui.y
 * @create 2021-09-04 1:32 下午
 **/
public class FooBarDemo1 {


    public static void main( String[] args ) {


        PrintTask task = new PrintTask( 4 );

        new Thread(task::printFoo).start();
        new Thread(task::printBoo).start();

    }


    /**
     * 共享资源
     */
    public static class PrintTask {

        private int count;
        private boolean flag = false;

        public PrintTask(int count) {
            this.count = count;
        }


        public void printFoo()  {
            for (int i = 0;i < count;i++) {
                //每次打印，先尝试获取锁
                synchronized (this) {
                    if (!flag) {
                        System.out.print("foo");
                        flag = true;
                        this.notify();
                    }
                    //执行完则等待（释放锁）
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        public void printBoo()  {
            for (int i = 0;i < count;i++) {
                synchronized (this) {
                    if (flag) {
                        System.out.print("bar");
                        flag = false;
                        this.notify();
                    }
                    //执行完则等待（释放锁）
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }



}
