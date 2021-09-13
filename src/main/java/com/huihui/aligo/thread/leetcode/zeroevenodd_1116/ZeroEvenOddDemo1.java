package com.huihui.aligo.thread.leetcode.zeroevenodd_1116;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * leetcode题目：三个线程，每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 * 输入：n = 2
 * 输出："0102"
 * 说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用od
 *
 * 方案：ReentrantLock + Condition + 标志位
 *
 * 性能：执行耗时:8 ms,击败了82.66% 的Java用户 内存消耗:37.4 MB,击败了86.21% 的Java用户
 *
 * @author minghui.y
 * @create 2021-09-08 4:54 下午
 **/
public class ZeroEvenOddDemo1 {


    public static void main( String[] args ) {
        int endNum = 9;

        PrintTask task = new PrintTask( endNum );
        new Thread(task::zero).start();
        new Thread(task::even).start();
        new Thread(task::odd).start();


    }


    public static class PrintTask {

        private Lock lock = new ReentrantLock();
        private Condition conditionZero = lock.newCondition();
        private Condition conditionEven = lock.newCondition();
        private Condition conditionOdd = lock.newCondition();
        private int flag = 0;

        private int endNum;
        public PrintTask(int endNum) {
            this.endNum = endNum;
        }

        /**
         * 打印0
         */
        public void zero() {
            lock.lock();
            //注意i
            for (int i = 1;i <= endNum;i++) {
                if (flag != 0) {
                    try {
                        conditionZero.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print("0");
                //打印0之后，判断唤醒哪个线程
                if (i % 2 == 0) {
                    //偶数
                    flag = 2;
                    conditionEven.signal();
                } else {
                    //奇数
                    flag = 1;
                    conditionOdd.signal();
                }
            }
            lock.unlock();
        }

        /**
         * 打印偶数
         */
        private void even() {
            lock.lock();
            //注意i
            for (int i = 2;i <= endNum;i += 2) {
                if (flag != 2) {
                    try {
                        conditionEven.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(i);
                flag = 0;
                conditionZero.signal();
            }
            lock.unlock();
        }


        /**
         * 打印奇数
         */
        private void odd() {
            lock.lock();
            //注意i
            for (int i = 1;i <= endNum;i += 2) {
                if (flag != 1) {
                    try {
                        conditionOdd.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(i);
                flag = 0;
                conditionZero.signal();
            }
            lock.unlock();
        }



    }

}
