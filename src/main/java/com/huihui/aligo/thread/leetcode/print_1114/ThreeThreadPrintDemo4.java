package com.huihui.aligo.thread.leetcode.print_1114;

/**
 * leetcode题：三个线程按顺序打印
 * 描述：线程A打印first，线程B打印second，线程C打印third
 *
 * 方案：CAS自旋锁 + Thread.yield() + 标志位
 * 性能：
 *     执行耗时:11 ms,击败了90.55% 的Java用户
 * 	   内存消耗:37.8 MB,击败了72.86% 的Java用户
 * @author minghui.y
 * @create 2021-09-05 1:15 下午
 **/
public class ThreeThreadPrintDemo4 {

    public static void main( String[] args ) {

        ThreeThreadPrintDemo1.PrintTask printTask = new ThreeThreadPrintDemo1.PrintTask();

        new Thread(printTask::printFirst).start();
        new Thread(printTask::printSecond).start();
        new Thread(printTask::printThird).start();

    }



    public static class PrintTask {

        private int flag = 1;

        public void printFirst() {
            while (flag != 1) {
                Thread.yield();
            }
            System.out.println("first");
            flag = 2;

        }

        public void printSecond() {
            while (flag != 2) {
                Thread.yield();
            }
            System.out.println("second");
            flag = 3;
        }

        public void printThird() {
            while (flag != 3) {
                Thread.yield();
            }
            System.out.println("third");
        }
    }



}
