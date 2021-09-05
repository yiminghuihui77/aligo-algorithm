package com.huihui.aligo.thread.leetcode.print_1114;

import java.util.concurrent.CountDownLatch;

/**
 * leetcode题：三个线程按顺序打印
 * 描述：线程A打印first，线程B打印second，线程C打印third
 *
 * 方案：两个CountDownLatch
 * 性能：
 *     执行耗时:13 ms,击败了55.02% 的Java用户
 * 	   内存消耗:37.7 MB,击败了74.19% 的Java用户
 * @author minghui.y
 * @create 2021-09-05 1:38 下午
 **/
public class ThreeThreadPrintDemo6 {

    public static void main( String[] args ) {

        ThreeThreadPrintDemo1.PrintTask printTask = new ThreeThreadPrintDemo1.PrintTask();

        new Thread(printTask::printFirst).start();
        new Thread(printTask::printSecond).start();
        new Thread(printTask::printThird).start();

    }

    public static class  PrintTask {


        private CountDownLatch countDownLatchB = new CountDownLatch( 1 );
        private CountDownLatch countDownLatchC = new CountDownLatch( 1 );

        public void printFirst() {
            System.out.print("first");
            countDownLatchB.countDown();
        }

        public void printSecond() throws InterruptedException {
            countDownLatchB.await();
            System.out.print("second");
            countDownLatchC.countDown();
        }

        public void printThird() throws InterruptedException {
            countDownLatchC.await();
            System.out.print("third");
        }

    }

}
