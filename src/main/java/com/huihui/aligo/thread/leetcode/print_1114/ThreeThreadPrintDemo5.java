package com.huihui.aligo.thread.leetcode.print_1114;

import java.util.concurrent.Semaphore;

/**
 * leetcode题：三个线程按顺序打印
 * 描述：线程A打印first，线程B打印second，线程C打印third
 *
 * 方案：两个Semaphore
 *
 * 性能：（5个demo中性能最优？）
 *     执行耗时:11 ms,击败了90.55% 的Java用户
 * 	   内存消耗:37.4 MB,击败了96.07% 的Java用户
 *
 * @author minghui.y
 * @create 2021-09-05 1:26 下午
 **/
public class ThreeThreadPrintDemo5 {


    public static void main( String[] args ) {

        ThreeThreadPrintDemo1.PrintTask printTask = new ThreeThreadPrintDemo1.PrintTask();

        new Thread(printTask::printFirst).start();
        new Thread(printTask::printSecond).start();
        new Thread(printTask::printThird).start();

    }

    public static class PrintTask {

        private Semaphore semaphoreB = new Semaphore( 0 );
        private Semaphore semaphoreC = new Semaphore( 0 );


        public void printFirst() {
            System.out.print("first");
            semaphoreB.release();
        }

        public void printSecond() throws InterruptedException {
            semaphoreB.acquire();
            System.out.print("second");
            semaphoreC.release();
        }

        public void printThird() throws InterruptedException {
            semaphoreC.acquire();
            System.out.print("third");
        }


    }




}
