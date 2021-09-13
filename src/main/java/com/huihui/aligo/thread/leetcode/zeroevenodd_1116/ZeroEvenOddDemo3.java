package com.huihui.aligo.thread.leetcode.zeroevenodd_1116;

import java.util.concurrent.Semaphore;

/**
 * leetcode题目：三个线程，每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 *   输入：n = 2
 *   输出："0102"
 *   说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用od
 *
 *   方案：三个信号量Semaphore
 *
 *   性能：执行耗时:9 ms,击败了37.68% 的Java用户 内存消耗:37.7 MB,击败了37.37% 的Java用户
 *
 * @author minghui.y
 * @create 2021-09-13 11:09 上午
 **/
public class ZeroEvenOddDemo3 {

    public static void main( String[] args ) {
        int input = 9;
        PrintTask task = new PrintTask( input );

        new Thread(task::zero).start();
        new Thread(task::even).start();
        new Thread(task::odd).start();
    }




    public static class PrintTask {

        private int input;
        public PrintTask(int input) {
            this.input = input;
        }
        //初始值为1，让线程zero能先被执行
        private Semaphore semaphoreZero = new Semaphore( 1 );
        private Semaphore semaphoreEven = new Semaphore( 0 );
        private Semaphore semaphoreOdd = new Semaphore( 0 );


        public void zero() {
            for (int i = 1;i <= input;i++ ) {
                try {
                    semaphoreZero.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(0);
                if (i % 2 == 0) {
                    semaphoreEven.release();
                } else {
                    semaphoreOdd.release();
                }
            }
        }


        public void even() {
            for (int i = 2;i <= input; i+= 2) {
                try {
                    semaphoreEven.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(i);
                semaphoreZero.release();
            }
        }

        public void odd() {
            for (int i = 1;i <= input; i+= 2) {
                try {
                    semaphoreOdd.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(i);
                semaphoreZero.release();
            }
        }


    }

}
