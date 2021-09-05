package com.huihui.aligo.thread.leetcode.foobar_1115;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * leetcode题：交替打印FooBar
 * 方案：ReentrantLock + Condition + flag
 * @author minghui.y
 * @create 2021-09-04 2:31 下午
 **/
public class FooBarDemo2 {

    public static void main( String[] args ) {


        FooBarDemo1.PrintTask task = new FooBarDemo1.PrintTask( 4 );

        new Thread(task::printFoo).start();
        new Thread(task::printBoo).start();

    }

    /**
     * 共享资源
     */
    public static class PrintTask {

        private int count;
        private boolean flag = false;
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public PrintTask(int count) {
            this.count = count;
        }


        public void printFoo()  {
            for (int i = 0;i < count;i++) {
                //每次打印，先尝试获取锁
               lock.lock();
               if (!flag) {
                   System.out.print("foo");
                   flag = true;
                   condition.signal();
               }
               //等待
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }

        public void printBoo()  {
            for (int i = 0;i < count;i++) {
                lock.lock();
                if (flag) {
                    System.out.print("bar");
                    flag = false;
                    condition.signal();
                }
                //等待
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }
    }
}
