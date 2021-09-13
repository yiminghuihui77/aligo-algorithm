package com.huihui.aligo.thread.leetcode.print_1114;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * leetcode题：三个线程按顺序打印
 * 描述：线程A打印first，线程B打印second，线程C打印third
 *
 * 方案：阻塞队列
 *
 * 性能：
 *     执行耗时:12 ms,击败了77.44% 的Java用户
 * 	   内存消耗:38.1 MB,击败了35.63% 的Java用户
 *
 *
 * @author minghui.y
 * @create 2021-09-05 12:26 下午
 **/
public class ThreeThreadPrintDemo1 {

    public static void main( String[] args ) {

        PrintTask printTask = new PrintTask();

        new Thread(printTask::printFirst).start();
        new Thread(printTask::printSecond).start();
        new Thread(printTask::printThird).start();

    }




    public static class PrintTask {
        /**
         * 利用阻塞队列实现
         * */
        private BlockingQueue<String> firstQueue = new LinkedBlockingDeque<>(1);
        private BlockingQueue<String> secondQueue = new LinkedBlockingDeque<>(1);
        private BlockingQueue<String> thirdQueue = new LinkedBlockingDeque<>(1);



        public void printFirst() {
            try {
                firstQueue.put( "first" );
                System.out.print("first");
                secondQueue.put( "second" );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


        public void printSecond() {
            try {
                secondQueue.take();
                System.out.print("second");
                thirdQueue.put( "third" );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void printThird() {
            try {
                thirdQueue.take();
                System.out.print("third");
                firstQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }






    }

}
