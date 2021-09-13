package com.huihui.aligo.thread.leetcode.zeroevenodd_1116;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * leetcode题目：三个线程，每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 *  输入：n = 2
 *  输出："0102"
 *  说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用od
 *
 *  方案：阻塞队列 + 标志位
 *
 *  结果：leetcode提交失败，超出时间限制；本地单测没问题
 *
 * @author minghui.y
 * @create 2021-09-08 8:45 下午
 **/
public class ZeroEvenOddDemo2 {


    public static void main( String[] args ) {
        int endNum = 99;
        PrintTask task = new PrintTask( endNum );

        new Thread(task::zero).start();
        new Thread(task::even).start();
        new Thread(task::odd).start();
    }


    public static class PrintTask {

        private BlockingQueue<Integer> queueZero = new LinkedBlockingDeque<>(1);
        private BlockingQueue<Integer> queueEven = new LinkedBlockingDeque<>(1);
        private BlockingQueue<Integer> queueOdd = new LinkedBlockingDeque<>(1);
        private volatile int flag = 0;

        private int endNum;
        public PrintTask(int endNum) {
            this.endNum = endNum;
        }

        /**
         * 打印0
         */
        public void zero() {
            for (int i = 1;i <= endNum;i++) {
               try {
                   //注意：用while而不是if
                   while (flag != 0) {
                       queueZero.take();
                   }
                   System.out.print( 0);
                   if (i % 2 == 0) {
                       //偶数
                       flag = 2;
                       queueEven.put( i );
                   } else {
                       //奇数
                       flag = 1;
                       queueOdd.put( i );
                   }
               } catch (Exception e) {
                   e.printStackTrace();
               }

            }
        }


        /**
         * 打印偶数
         */
        private void even() {
            for (int i = 2;i <= endNum;i+= 2) {
                try {
                    //注意：用while而不是if
                    while (flag != 2) {
                        queueEven.take();
                    }
                    System.out.print(i);
                    flag = 0;
                    queueZero.put( 0 );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        /**
         * 打印奇数
         */
        private void odd() {
            for (int i = 1;i <= endNum;i+= 2) {
                try {
                    //注意：用while而不是if
                    while (flag != 1) {
                        queueOdd.take();
                    }
                    System.out.print(i);
                    flag = 0;
                    queueZero.put( 0 );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
