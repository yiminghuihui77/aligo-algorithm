package com.huihui.aligo.thread.leetcode.zeroevenodd_1116;

/**
 * leetcode题目：三个线程，每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 *   输入：n = 2
 *   输出："0102"
 *   说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用od
 *
 *   方案：Thread.yield() + 标志位
 *
 *   性能：执行耗时:8 ms,击败了82.66% 的Java用户 内存消耗:37.3 MB,击败了92.50% 的Java用户
 *
 * @author minghui.y
 * @create 2021-09-13 11:32 上午
 **/
public class ZeroEvenOddDemo4 {

    public static void main( String[] args ) {
        int endNum = 99;
       PrintTask task = new PrintTask( endNum );

        new Thread(task::zero).start();
        new Thread(task::even).start();
        new Thread(task::odd).start();
    }

    public static class PrintTask {

        private int input;
        public PrintTask(int input) {
            this.input = input;
        }

        private int flag = 0;

        public void zero() {
            for (int i = 1;i <= input;i++) {
                while (flag != 0) {
                    Thread.yield();
                }
                System.out.print(0);
                if (i % 2 == 0) {
                    flag = 2;
                } else {
                    flag = 1;
                }
            }
        }

        public void even() {
            for (int i = 2;i <= input;i+= 2) {
                while (flag != 2) {
                    Thread.yield();
                }
                System.out.print(i);
                flag = 0;
            }
        }

        public void odd() {
            for (int i = 1;i <= input;i+= 2) {
                while (flag != 1) {
                    Thread.yield();
                }
                System.out.print(i);
                flag = 0;
            }
        }

    }


}
