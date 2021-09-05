package com.huihui.aligo.thread.leetcode.print_1114;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * leetcode题：三个线程按顺序打印
 * 描述：线程A打印first，线程B打印second，线程C打印third
 *
 * 方案：ReentrantLock + Condition + 标志位
 * 性能：
 *      执行耗时:12 ms,击败了77.44% 的Java用户
 * 		内存消耗:38.4 MB,击败了5.45% 的Java用户
 * @author minghui.y
 * @create 2021-09-05 12:57 下午
 **/
public class ThreeThreadPrintDemo3 {

    public static void main( String[] args ) {

        ThreeThreadPrintDemo1.PrintTask printTask = new ThreeThreadPrintDemo1.PrintTask();

        new Thread(printTask::printFirst).start();
        new Thread(printTask::printSecond).start();
        new Thread(printTask::printThird).start();

    }




    public static class PrintClass {

        private int flag = 1;
        private ReentrantLock lock = new ReentrantLock(true);
        private Condition conditionB = lock.newCondition();
        private Condition conditionC = lock.newCondition();


        public void printFirst() throws InterruptedException {
            lock.lock();
            System.out.print("first");
            flag = 2;
            conditionB.signal();
            lock.unlock();
        }

        public void printSecond() throws InterruptedException {
            lock.lock();
            if (flag != 2) {
                conditionB.await();
            }
            System.out.print("second");
            flag = 3;
            conditionC.signal();
            lock.unlock();
        }

        public void printThird() throws InterruptedException {
            lock.lock();
            if (flag != 3) {
                conditionC.await();
            }
            System.out.print("third");
            lock.unlock();
        }

    }



}
